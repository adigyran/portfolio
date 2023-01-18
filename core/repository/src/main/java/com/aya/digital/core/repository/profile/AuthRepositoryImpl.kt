package com.aya.digital.core.repository.profile

import com.auth0.android.jwt.JWT
import com.aya.digital.core.data.mappers.preferences.AuthUserDataMapper
import com.aya.digital.core.data.repositories.profile.AuthRepository
import com.aya.digital.core.datasource.ProfileDataSource
import com.aya.digital.core.datastore.PatientAppAuthDataSource
import com.aya.digital.core.network.di.AppAuth
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.internal.operators.completable.CompletableFromSingle
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.EndSessionRequest
import net.openid.appauth.TokenRequest
import java.util.*

class AuthRepositoryImpl(
    private val appAuth: AppAuth,
    private val authDataStore: PatientAppAuthDataSource,
    private val authUserDataMapper: AuthUserDataMapper,
    private val profileDataSource: ProfileDataSource
) : AuthRepository {

    private var isPatient: Boolean? = null

    override fun getAuthRequest(): AuthorizationRequest = appAuth.getAuthRequest()

    override fun getEndSessionRequest(): EndSessionRequest = appAuth.getEndSessionRequest()

    override fun isPatient(): Single<RequestResult<Boolean>> {
        return isPatient?.let { Single.just(RequestResult.Success(it)) }
            ?: tokenIsPatient().map { optionalPatientValue ->
                if (optionalPatientValue.isEmpty) RequestResult.Error.NoRoleDefined
                else {
                    this@AuthRepositoryImpl.isPatient = optionalPatientValue.get()
                    RequestResult.Success(optionalPatientValue.get())
                }
            }
    }

    override fun performTokenRequest(tokenRequest: TokenRequest): Completable =
        appAuth
            .performTokenRequest(tokenRequest)
            .flatMapCompletable { tokens -> authDataStore.saveAuthData(authUserDataMapper.mapFrom(tokens)) }

    private fun tokenIsPatient() = authDataStore.authUserData
        .map { authUserData -> checkAccessToken(authUserData.accessToken) }
        .firstOrError()

    override fun clear() {
        isPatient = null
    }

    override fun logout(): Completable  = authDataStore.authUserData
        .flatMapCompletable {  authUserData -> profileDataSource.logout("aya-client",authUserData.refreshToken).andThen(authDataStore.clearAuthData()) }


    private fun checkAccessToken(accessToken: String): Optional<Boolean> {
        val jwt = JWT(accessToken)
        val roles = jwt.getClaim("roles").asArray(String::class.java)

        val isDoctor = roles.contains("ROLE_PRACTITIONER")
        val isPatient = roles.contains("ROLE_PATIENT")

        if (!isDoctor && !isPatient) return Optional.empty()

        //Doctor role is dominant
        return Optional.of(!isDoctor)
    }
}
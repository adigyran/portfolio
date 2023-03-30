package com.aya.digital.core.repository.auth

import android.net.Uri
import androidx.core.net.toUri
import com.aya.digital.core.data.mappers.profile.LoginResultMapper
import com.aya.digital.core.data.profile.LoginResult
import com.aya.digital.core.data.profile.repository.AuthRepository
import com.aya.digital.core.data.profile.repository.AuthTokenRepository
import com.aya.digital.core.datasource.AuthDataSource
import com.aya.digital.core.datasource.TokenDataSource
import com.aya.digital.core.datastore.HealthAppAuthDataSource
import com.aya.digital.core.ext.*
import com.aya.digital.core.network.model.auth.Tokens
import com.aya.digital.core.network.model.request.LoginBody
import com.aya.digital.core.network.model.request.RefreshTokenBody
import com.aya.digital.core.network.model.request.RegistrationBody
import com.aya.digital.core.networkbase.CommonUtils
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import net.openid.appauth.*
import timber.log.Timber
import kotlin.coroutines.suspendCoroutine

internal class AuthTokenRepositoryImpl(
    private val authDataStore: HealthAppAuthDataSource,
    private val tokenDataSource: TokenDataSource,
    private val loginResultMapper: LoginResultMapper
) : AuthTokenRepository {
    override fun refreshAndSaveTokens(): Single<RequestResult<Boolean>> =
        getRefreshToken()
            .flatMapResult({refreshToken ->
                           if(refreshToken.isNotBlank()) {
                               tokenDataSource.refreshToken(RefreshTokenBody(refreshToken = refreshToken))
                                   .retryOnError()
                                   .retrofitResponseToResult(CommonUtils::mapServerErrors)
                                   .mapResult({ loginResultMapper.mapFrom(it).asResult() }, { it })
                                   .flatMapResult({
                                       if (it.token.isNotBlank() && it.refreshToken.isNotBlank()) saveToken(
                                           it.token,
                                           it.refreshToken
                                       ) else Single.just(false.asResult())
                                   }, { Single.just(it) })
                           } else Single.just(false.asResult())
            },{Single.just(it)})

    private fun getRefreshToken() : Single<RequestResult<String>> =
        authDataStore.refreshTokenData
            .firstOrError()
            .map { it.asResult() }
            .doOnError {it.asErrorResult<String>() }

    private fun saveToken(token: String, refreshToken: String): Single<RequestResult<Boolean>> =
        authDataStore.saveAuthData(token, refreshToken)
            .map { true.asResult() }


    private val serviceConfiguration = AuthorizationServiceConfiguration(
        Uri.parse(AuthConfig.AUTH_URI),
        Uri.parse(AuthConfig.TOKEN_URI),
        null, // registration endpoint
        Uri.parse(AuthConfig.END_SESSION_URI)
    )

    override suspend fun performTokenRequest(
        authService: AuthorizationService,
        tokenRequest: TokenRequest,
    ): RequestResult<Tokens> {
        val tokens = performTokenRequestSuspend(authService, tokenRequest)
        Timber.tag("Oauth").d("6. Tokens accepted:\n access=${tokens.accessToken}\nrefresh=${tokens.refreshToken}")
        return tokens.asResult()
    }

    override suspend fun saveOAuthToken(
        token: String,
        refreshToken: String
    ): RequestResult<Boolean>{
        authDataStore.saveAuthDataSuspend(token, refreshToken)
        return true.asResult()
    }


    override fun getAuthRequest(): Single<RequestResult<AuthorizationRequest>> {
        val redirectUri = AuthConfig.CALLBACK_URL.toUri()

        val authRequest = AuthorizationRequest.Builder(
            serviceConfiguration,
            AuthConfig.CLIENT_ID,
            AuthConfig.RESPONSE_TYPE,
            redirectUri
        )
            .setScope(AuthConfig.SCOPE)
            .build()
        return Single.just(authRequest.asResult())
    }

    private suspend fun performTokenRequestSuspend(
        authService: AuthorizationService,
        tokenRequest: TokenRequest,
    ): Tokens {
        return suspendCoroutine { continuation ->
            authService.performTokenRequest(tokenRequest, getClientAuthentication()) { response, ex ->
                Timber.d("$response $ex")
                when {
                    response != null -> {
                        //получение токена произошло успешно

                        val tokens = Tokens(
                            accessToken = response.accessToken.orEmpty(),
                            refreshToken = response.refreshToken.orEmpty(),
                        )
                        continuation.resumeWith(Result.success(tokens))
                    }
                    //получение токенов произошло неуспешно, показываем ошибку
                    ex != null -> { continuation.resumeWith(Result.failure(ex)) }
                    else -> error("unreachable")
                }
            }
        }
    }
    private fun getClientAuthentication(): ClientAuthentication {
        val clientSecretPost = ClientSecretPost(AuthConfig.CLIENT_SECRET)
        return clientSecretPost
    }

    private object AuthConfig {
        const val AUTH_URI = "https://auth-v3.aya-doc.com/auth/realms/aya-realm/protocol/openid-connect/auth?kc_idp_hint=google&"
        const val TOKEN_URI = "https://auth-v3.aya-doc.com/auth/realms/aya-realm/protocol/openid-connect/token"
        const val END_SESSION_URI = "https://auth-v3.aya-doc.com/auth/realms/aya-realm/protocol/openid-connect/logout"
        const val RESPONSE_TYPE = ResponseTypeValues.CODE
        const val SCOPE = "email"

        const val CLIENT_ID = "mobile-client"
        const val CLIENT_SECRET = "JXnfDtUdxow13VyKu4771WjCOyyijeWG"
        const val CALLBACK_URL = "com.aya.digital.healthapp://auth/login"
        const val LOGOUT_CALLBACK_URL = "com.aya.digital.healthapp://auth/logout"
    }
}
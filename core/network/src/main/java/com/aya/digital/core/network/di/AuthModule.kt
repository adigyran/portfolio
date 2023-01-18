package com.aya.digital.core.network.di

import android.net.Uri
import androidx.core.net.toUri
import com.aya.digital.core.network.AuthConfig
import com.aya.digital.core.network.model.auth.Tokens
import io.reactivex.rxjava3.core.Single
import net.openid.appauth.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun authModule() = DI.Module("authModule") {

    bind<AuthorizationService>() with singleton { AuthorizationService(instance()) }

    bind<ClientAuthentication>() with singleton {
        return@singleton NoClientAuthentication.INSTANCE
    }

    bind<AuthorizationServiceConfiguration>() with singleton {
        return@singleton AuthorizationServiceConfiguration(
            Uri.parse(AuthConfig.AUTH_URI),
            Uri.parse(AuthConfig.TOKEN_URI),
            null, // registration endpoint
            Uri.parse(AuthConfig.END_SESSION_URI)
        )
    }
    bind<AppAuth>() with singleton {
        return@singleton AppAuthImpl(instance(), instance(), instance())
    }
}

interface AppAuth {
    fun getAuthRequest(): AuthorizationRequest

    fun getEndSessionRequest(): EndSessionRequest

    fun getRefreshTokenRequest(refreshToken: String): TokenRequest

    fun performTokenRequest(tokenRequest: TokenRequest): Single<Tokens>
}

private class AppAuthImpl(
    private val clientAuthentication: ClientAuthentication,
    private val serviceConfiguration: AuthorizationServiceConfiguration,
    private val authService: AuthorizationService
) : AppAuth {

    override fun getAuthRequest(): AuthorizationRequest {
        val redirectUri = AuthConfig.CALLBACK_URL.toUri()

        return AuthorizationRequest.Builder(
            serviceConfiguration,
            AuthConfig.CLIENT_ID,
            AuthConfig.RESPONSE_TYPE,
            redirectUri
        )
            .setScope(AuthConfig.SCOPE)
            .build()
    }

    override fun getEndSessionRequest(): EndSessionRequest {
        return EndSessionRequest.Builder(serviceConfiguration)
            .setPostLogoutRedirectUri(AuthConfig.LOGOUT_CALLBACK_URL.toUri())
            .build()
    }

    override fun getRefreshTokenRequest(refreshToken: String): TokenRequest {
        return TokenRequest.Builder(
            serviceConfiguration,
            AuthConfig.CLIENT_ID
        )
            .setGrantType(GrantTypeValues.REFRESH_TOKEN)
            .setScopes(AuthConfig.SCOPE)
            .setRefreshToken(refreshToken)
            .build()
    }

    override fun performTokenRequest(tokenRequest: TokenRequest): Single<Tokens> {
        TODO("Not yet implemented")
    }

}
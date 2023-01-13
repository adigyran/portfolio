package com.aya.digital.core.network.di

import android.net.Uri
import androidx.core.net.toUri
import com.aya.digital.core.network.AuthConfig
import net.openid.appauth.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.eagerSingleton
import org.kodein.di.instance

fun authModule() = DI.Module("authModule") {
    bind<ClientAuthentication>() with eagerSingleton {
        return@eagerSingleton NoClientAuthentication.INSTANCE
    }

    bind<AuthorizationServiceConfiguration>() with eagerSingleton {
        return@eagerSingleton AuthorizationServiceConfiguration(
            Uri.parse(AuthConfig.AUTH_URI),
            Uri.parse(AuthConfig.TOKEN_URI),
            null, // registration endpoint
            Uri.parse(AuthConfig.END_SESSION_URI)
        )
    }
    bind<AppAuth>() with eagerSingleton {
        return@eagerSingleton AppAuthImpl(instance(),instance())
    }
}

interface AppAuth {
    fun getAuthRequest(): AuthorizationRequest

    fun getEndSessionRequest(): EndSessionRequest

    fun getRefreshTokenRequest(refreshToken: String): TokenRequest
}

private class AppAuthImpl(private val clientAuthentication: ClientAuthentication, private val serviceConfiguration : AuthorizationServiceConfiguration) : AppAuth {

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
}
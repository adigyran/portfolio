package com.aya.digital.core.network.di

import android.net.Uri
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
        TODO("Not yet implemented")
    }

    override fun getEndSessionRequest(): EndSessionRequest {
        TODO("Not yet implemented")
    }

    override fun getRefreshTokenRequest(refreshToken: String): TokenRequest {
        TODO("Not yet implemented")
    }
}
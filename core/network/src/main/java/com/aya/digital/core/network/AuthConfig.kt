package com.aya.digital.core.network

import net.openid.appauth.ResponseTypeValues

internal object AuthConfig {
    const val AUTH_URI = "https://auth.aya-doc.com/auth/realms/aya-realm/protocol/openid-connect/auth"
    const val TOKEN_URI = "https://auth.aya-doc.com/auth/realms/aya-realm/protocol/openid-connect/token"
    const val END_SESSION_URI = "https://auth.aya-doc.com/auth/realms/aya-realm/protocol/openid-connect/logout"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "email profile"

    const val CLIENT_ID = "aya-client"
    const val CLIENT_SECRET = ""
    const val CALLBACK_URL = "com.aya.digital.healthapp://auth/login"
    const val LOGOUT_CALLBACK_URL = "com.aya.digital.healthapp://auth/logout"
}
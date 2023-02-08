package com.aya.digital.core.data.preferences

data class AuthUserData(
    val refreshToken : String,
    val accessToken : String,
    val idToken : String
)

package com.aya.digital.core.network.model.auth

data class Tokens(
    val idToken: String,
    val accessToken: String,
    val refreshToken: String,
)
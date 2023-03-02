package com.aya.digital.core.network.model.auth

data class Tokens(
    val accessToken: String,
    val refreshToken: String,
)
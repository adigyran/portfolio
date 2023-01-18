package com.aya.digital.core.data.model.preferences

data class UserData(
    val onBoardingSeen : Boolean,
    val disclaimerSeen : Boolean,
    val refreshToken : Boolean,
    val accessToken : Boolean,
    val idToken : Boolean
)

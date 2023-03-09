package com.aya.digital.core.network.model.response.auth

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserKeyResponse(val stub:String?=null) {

}
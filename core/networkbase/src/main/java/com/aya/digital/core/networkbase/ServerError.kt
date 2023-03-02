package com.aya.digital.core.networkbase

import com.aya.digital.core.networkbase.server.IServerError

data class ServerError(
    val _code: String,
    var _detail: String
) : IServerError {
    override fun getCode(): String = _code

    override fun getDetail(): String = _detail
}
package com.aya.digital.core.data.base.dataprocessing

class ServerErrorModel( val _code: String,
                        var _detail: String) : IServerErrorModel {
    override fun getCode(): String = _code

    override fun getDetail(): String = _detail
}
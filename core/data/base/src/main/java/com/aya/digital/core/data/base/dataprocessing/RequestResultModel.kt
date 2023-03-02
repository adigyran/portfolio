package com.aya.digital.core.data.base.dataprocessing

import com.aya.digital.core.networkbase.server.IServerError
import com.aya.digital.core.networkbase.server.RequestResult

sealed class RequestResultModel<out T> {
    data class Success<out T>(val data: T) : RequestResultModel<T>()

    sealed class Error : RequestResultModel<Nothing>() {
        data class HttpCode400(
            val errorList: List<IServerError>,
        ) : Error()

        object HttpCode401 : Error()
        object HttpCode403 : Error()
        object HttpCode404 : Error()
        data class HttpCode406(
            val errorList: List<IServerError>
        ) : Error()
        object HttpCode409 : Error()
        object HttpCodeAnother : Error()

        object UnknownHost : Error()
        object SocketTimeout : Error()
        object NoRoleDefined : Error()
        data class JsonParsingError(val throwable: Throwable) : Error()

        data class Another(val throwable: Throwable) : Error()
    }

    inline fun <V> processResult(successHandler: (T) -> V, errorHandler: (Error) -> V): V =
        when (this) {
            is Success -> successHandler(this.data)
            is Error -> errorHandler(this)
        }
}

fun <T> T.asResultModel(): RequestResultModel<T> = RequestResultModel.Success(this)

fun RequestResult.Error.toModelError(): RequestResultModel.Error =
    when(this)
    {
        is RequestResult.Error.HttpCode400 -> RequestResultModel.Error.HttpCode400(errorList)
        is RequestResult.Error.Another -> RequestResultModel.Error.Another(throwable)
        RequestResult.Error.HttpCode401 -> RequestResultModel.Error.HttpCode401
        RequestResult.Error.HttpCode403 -> RequestResultModel.Error.HttpCode403
        RequestResult.Error.HttpCode404 -> RequestResultModel.Error.HttpCode404
        is RequestResult.Error.HttpCode406 -> RequestResultModel.Error.HttpCode406(errorList)
        RequestResult.Error.HttpCode409 -> RequestResultModel.Error.HttpCode409
        RequestResult.Error.HttpCodeAnother -> RequestResultModel.Error.HttpCodeAnother
        is RequestResult.Error.JsonParsingError -> RequestResultModel.Error.JsonParsingError(throwable)
        RequestResult.Error.NoRoleDefined -> RequestResultModel.Error.NoRoleDefined
        RequestResult.Error.SocketTimeout -> RequestResultModel.Error.SocketTimeout
        RequestResult.Error.UnknownHost -> RequestResultModel.Error.UnknownHost
    }


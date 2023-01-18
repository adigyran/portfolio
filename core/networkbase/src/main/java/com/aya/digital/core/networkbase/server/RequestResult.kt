package com.aya.digital.core.networkbase.server

sealed class RequestResult<out T> {
    data class Success<out T>(val data: T) : RequestResult<T>()

    sealed class Error : RequestResult<Nothing>() {
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
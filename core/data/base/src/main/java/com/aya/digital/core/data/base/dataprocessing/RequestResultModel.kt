package com.aya.digital.core.data.base.dataprocessing

import com.aya.digital.core.networkbase.server.IServerError
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

sealed class RequestResultModel<out T> {
    data class Success<out T>(val data: T) : RequestResultModel<T>()

    sealed class Error : RequestResultModel<Nothing>() {
        data class HttpCode400(
            val errorList: List<IServerErrorModel>,
        ) : Error()

        object HttpCode401 : Error()
        object HttpCode403 : Error()
        object HttpCode404 : Error()
        data class HttpCode406(
            val errorList: List<IServerErrorModel>
        ) : Error()

        data class HttpCode409(
            val errorList: List<IServerErrorModel>
        ) : Error()

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

fun IServerError.asServerErrorModel() = ServerErrorModel(this.getCode(), this.getDetail())

fun RequestResult.Error.toModelError(): RequestResultModel.Error =
    when (this) {
        is RequestResult.Error.HttpCode400 -> RequestResultModel.Error.HttpCode400(errorList.map { it.asServerErrorModel() })
        is RequestResult.Error.Another -> RequestResultModel.Error.Another(throwable)
        RequestResult.Error.HttpCode401 -> RequestResultModel.Error.HttpCode401
        RequestResult.Error.HttpCode403 -> RequestResultModel.Error.HttpCode403
        RequestResult.Error.HttpCode404 -> RequestResultModel.Error.HttpCode404
        is RequestResult.Error.HttpCode406 -> RequestResultModel.Error.HttpCode406(errorList.map { it.asServerErrorModel() })
        is RequestResult.Error.HttpCode409 -> RequestResultModel.Error.HttpCode409(errorList.map { it.asServerErrorModel() })
        RequestResult.Error.HttpCodeAnother -> RequestResultModel.Error.HttpCodeAnother
        is RequestResult.Error.JsonParsingError -> RequestResultModel.Error.JsonParsingError(
            throwable
        )
        RequestResult.Error.NoRoleDefined -> RequestResultModel.Error.NoRoleDefined
        RequestResult.Error.SocketTimeout -> RequestResultModel.Error.SocketTimeout
        RequestResult.Error.UnknownHost -> RequestResultModel.Error.UnknownHost
    }


fun <T, V : Any> Observable<RequestResultModel<T>>.mapResult(
    successHandler: (T) -> V,
    errorHandler: (RequestResultModel.Error) -> V,
): Observable<V> =
    this.map { it.processResult(successHandler, errorHandler) }

fun <T, V : Any> Flowable<RequestResultModel<T>>.mapResult(
    successHandler: (T) -> V,
    errorHandler: (RequestResultModel.Error) -> V,
): Flowable<V> =
    this.map { it.processResult(successHandler, errorHandler) }

fun <T, V : Any> Single<RequestResultModel<T>>.mapResult(
    successHandler: (T) -> V,
    errorHandler: (RequestResultModel.Error) -> V,
): Single<V> =
    this.map { it.processResult(successHandler, errorHandler) }



package com.aya.digital.core.ext

import com.aya.digital.core.networkbase.server.IServerError
import com.aya.digital.core.networkbase.server.RequestResult
import com.squareup.moshi.JsonDataException
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.exceptions.CompositeException
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

fun <T : Any> Observable<T>.delayAction(): Observable<T> = this.throttleFirst(500, TimeUnit.MILLISECONDS)

fun <T : Any> Flowable<T>.delayAction(): Flowable<T> = this.throttleFirst(500, TimeUnit.MILLISECONDS)

fun <T : Any> Observable<T>.observeOnMainThread(): Observable<T> =
    this.observeOn(AndroidSchedulers.mainThread())

fun <T> T.asResult(): RequestResult<T> = RequestResult.Success(this)

fun <T> Throwable.asErrorResult(errorMapper: (String) -> List<IServerError>): RequestResult<T> {
    when (this) {
        is HttpException -> {
            when (this.code()) {
                400 -> {
                    val errorString = this.response()?.errorBody()!!.string()
                    return RequestResult.Error.HttpCode400(errorMapper.invoke(errorString))
                }
                401 -> return RequestResult.Error.HttpCode401
                403 -> return RequestResult.Error.HttpCode403
                404 -> return RequestResult.Error.HttpCode404
                406 -> {
                    val errorString = this.response()?.errorBody()?.string() ?: ""
                    return RequestResult.Error.HttpCode406(errorMapper.invoke(errorString))
                }
                409 -> return RequestResult.Error.HttpCode409
                else -> return RequestResult.Error.HttpCodeAnother
            }
        }
        is UnknownHostException -> return RequestResult.Error.UnknownHost
        is SocketTimeoutException -> return RequestResult.Error.SocketTimeout
        is JsonDataException -> return RequestResult.Error.JsonParsingError(this)
    }

    return RequestResult.Error.Another(this)
}

fun <T : Any> Observable<T>.retrofitResponseToResult(errorMapper: (String) -> List<IServerError>): Observable<RequestResult<T>> =
    this.map { it.asResult() }
        .onErrorReturn {
            if (it is HttpException || it is IOException || it is JsonDataException) return@onErrorReturn it.asErrorResult<T>(
                errorMapper
            ) else throw it
        }

fun <T : Any> Observable<T>.retryOnError(
    retriesCount: Int = 3,
): Observable<T> =
    this
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .onErrorResumeNext { throwable: Throwable ->
            if (throwable is CompositeException) Observable.error(
                throwable.exceptions[0]
            ) else Observable.error(throwable)
        }
        .retryWhen(
            RetryWithDelay(
                retriesCount,
                1000,
                { t -> t is UnknownHostException || t is SocketTimeoutException })
        )

class RetryWithDelay(
    private val maxRetries: Int,
    private val retryDelayMillis: Int,
    private val retryIf: Function<Throwable, Boolean>,
    private val unit: TimeUnit = TimeUnit.MILLISECONDS,
) : Function<Observable<out Throwable>, Observable<*>> {
    private var retryCount: Int = 0

    override fun apply(attempts: Observable<out Throwable>): Observable<*> {
        return attempts
            .doOnNext { println("RetryWithDelay $it") }
            .flatMap { throwable ->
                if (retryIf.apply(throwable) && (maxRetries < 0 || ++retryCount < maxRetries)) {
                    // When this Flowable calls onNext, the original Flowable will be retried (i.e. re-subscribed).
                    Observable.timer(retryDelayMillis.toLong(), unit)
                } else Observable.error<Any>(
                    throwable
                ) // Max retries hit. Just pass the error along.
            }
    }
}

fun <T, V : Any> Observable<RequestResult<T>>.mapResult(
    successHandler: (T) -> V,
    errorHandler: (RequestResult.Error) -> V,
): Observable<V> =
    this.map { it.processResult(successHandler, errorHandler) }

fun <T, V : Any> Observable<RequestResult<T>>.flatMapResult(
    successHandler: (T) -> Observable<V>,
    errorHandler: (RequestResult.Error) -> Observable<V>,
): Observable<V> =
    this.flatMap { it.processResult(successHandler, errorHandler) }

inline fun <reified T : Any> justEffect(effect: T): Observable<T> = Observable
    .just(effect)
    .subscribeOn(Schedulers.io())
    .observeOnMainThread()

inline fun <reified T : Any> justEffect(effect1: T, effect2: T): Observable<T> = Observable
    .just(effect1, effect2)
    .subscribeOn(Schedulers.io())
    .observeOnMainThread()

inline fun <reified T : Any> emptyEffect(): Observable<T> = Observable
    .empty<T>()
    .subscribeOn(Schedulers.io())
    .observeOnMainThread()

inline fun <reified T : Any> fromCallableEffect(callable: Callable<T>): Observable<T> = Observable
    .fromCallable(callable)
    .subscribeOn(Schedulers.io())
    .observeOnMainThread()
package com.aya.digital.core.ext

import com.squareup.moshi.JsonDataException
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.exceptions.CompositeException
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import ru.ivan.core.server.base.IServerError
import ru.ivan.core.server.base.RequestResult
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

fun <T> Observable<T>.delayAction(): Observable<T> = this.throttleFirst(500, TimeUnit.MILLISECONDS)

fun <T> Flowable<T>.delayAction(): Flowable<T> = this.throttleFirst(500, TimeUnit.MILLISECONDS)

fun <T> Observable<T>.observeOnMainThread(): Observable<T> =
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

fun <T> Observable<T>.retrofitResponseToResult(errorMapper: (String) -> List<IServerError>): Observable<RequestResult<T>> =
    this.map { it.asResult() }
        .onErrorReturn {
            if (it is HttpException || it is IOException || it is JsonDataException) return@onErrorReturn it.asErrorResult<T>(
                errorMapper
            ) else throw it
        }

@Deprecated(
    message = "specificErrorHandler401 был сделан для того, чтобы на методах не требующих токена, при неверном токене не было 401 ошибки. этого можно добиться  просто не присылая токе в таких методах",
    replaceWith = ReplaceWith("retryOnError(retriesCount)"))
fun <T> Observable<T>.retryOnError(
    specificErrorHandler401: (() -> Unit)? = null,
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
        .retryWhen(RetryWithDelay(retriesCount, 1000, io.reactivex.functions.Function { t ->
            if (t is HttpException && t.code() == 401) {
                specificErrorHandler401?.invoke()
                return@Function true
            }
            false
        }))

fun <T> Observable<T>.retryOnError(
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
    private val retryIf: io.reactivex.functions.Function<Throwable, Boolean>,
    private val unit: TimeUnit = TimeUnit.MILLISECONDS,
) : io.reactivex.functions.Function<Observable<out Throwable>, Observable<*>> {
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

fun <T, V> Observable<RequestResult<T>>.mapResult(
    successHandler: (T) -> V,
    errorHandler: (RequestResult.Error) -> V,
): Observable<V> =
    this.map { it.processResult(successHandler, errorHandler) }

fun <T, V> Observable<RequestResult<T>>.flatMapResult(
    successHandler: (T) -> Observable<V>,
    errorHandler: (RequestResult.Error) -> Observable<V>,
): Observable<V> =
    this.flatMap { it.processResult(successHandler, errorHandler) }

inline fun <reified T> justEffect(effect: T): Observable<T> = Observable
    .just(effect)
    .subscribeOn(Schedulers.io())
    .observeOnMainThread()

inline fun <reified T> justEffect(effect1: T, effect2: T): Observable<T> = Observable
    .just(effect1, effect2)
    .subscribeOn(Schedulers.io())
    .observeOnMainThread()

inline fun <reified T> emptyEffect(): Observable<T> = Observable
    .empty<T>()
    .subscribeOn(Schedulers.io())
    .observeOnMainThread()

inline fun <reified T> fromCallableEffect(callable: Callable<T>): Observable<T> = Observable
    .fromCallable(callable)
    .subscribeOn(Schedulers.io())
    .observeOnMainThread()
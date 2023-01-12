package com.aya.digital.core.mvi

import android.content.Context
import com.aya.digital.core.networkbase.server.HttpCodeHandler
import com.aya.digital.core.networkbase.server.HttpResponseCode
import com.aya.digital.core.networkbase.server.RequestResult
import com.badoo.mvicore.element.*
import com.badoo.mvicore.feature.BaseFeature
import org.kodein.di.bindings.ScopeCloseable
import com.aya.digital.core.baseresources.R

open class BaseFeature<Wish : Any, Action : Any, in Effect : Any, State : Any, News : Any>(
    initialState: State,
    bootstrapper: Bootstrapper<Action>? = null,
    wishToAction: WishToAction<Wish, Action>,
    actor: Actor<State, Action, Effect>,
    reducer: Reducer<State, Effect>,
    postProcessor: PostProcessor<Action, Effect, State>? = null,
    newsPublisher: NewsPublisher<Action, Effect, State, News>? = null,
) : BaseFeature<Wish, Action, Effect, State, News>(
    initialState,
    bootstrapper,
    wishToAction,
    actor,
    reducer,
    postProcessor,
    newsPublisher
), ScopeCloseable {
    sealed class BaseNews {
        data class Message(val msg: String) : BaseNews()
    }

    companion object {
        fun processError(
            context: Context,
            error: RequestResult.Error,
            vararg httpHttpCodeHandlers: HttpCodeHandler,
        ): BaseNews? {
            when (error) {
                is RequestResult.Error.HttpCode400 -> {
                    if (httpHttpCodeHandlers.find { it.first == HttpResponseCode.CODE_400 }?.second?.invoke(
                            error.errorList
                        ) != true
                    ) {
                        when {
                            error.errorList.isNotEmpty() -> return BaseNews.Message(
                                error.errorList.first().getDetail()
                            )
                            else -> return BaseNews.Message(
                                context.getString(R.string.server_error)
                            )
                        }
                    }
                    return null
                }
                is RequestResult.Error.HttpCode401 -> {
                   // EventBus.getDefault().post(InvalidTokenEvent())
                    return null
                }
                is RequestResult.Error.HttpCode403 -> if (httpHttpCodeHandlers.find { it.first == HttpResponseCode.CODE_403 }?.second?.invoke(
                        listOf()
                    ) != true
                ) return BaseNews.Message(
                    context.getString(R.string.server_error)
                )
                is RequestResult.Error.HttpCode404 -> if (httpHttpCodeHandlers.find { it.first == HttpResponseCode.CODE_404 }?.second?.invoke(
                        listOf()
                    ) != true
                ) return BaseNews.Message(
                    context.getString(R.string.server_error)
                )
                is RequestResult.Error.HttpCode406 -> {
                    val message = if (error.errorList.isNotEmpty()) {
                        error.errorList.first().getDetail()
                    } else {
                        context.getString(R.string.api_deprecated)
                    }

                   // EventBus.getDefault().post(InvalidApiVersionEvent(message))
                    return null
                }
                is RequestResult.Error.HttpCode409 -> if (httpHttpCodeHandlers.find { it.first == HttpResponseCode.CODE_409 }?.second?.invoke(
                        listOf()
                    ) != true
                ) return BaseNews.Message(
                    context.getString(R.string.server_error)
                )
                is RequestResult.Error.HttpCodeAnother -> return BaseNews.Message(
                    context.getString(
                        R.string.server_error
                    )
                )
                is RequestResult.Error.UnknownHost -> return BaseNews.Message(
                    context.getString(R.string.no_internet_connection)
                )
                is RequestResult.Error.SocketTimeout -> return BaseNews.Message(
                    context.getString(R.string.server_error)
                )
                is RequestResult.Error.JsonParsingError -> {
                    error.throwable.printStackTrace()
                    return BaseNews.Message(
                        context.getString(R.string.error_occupied)
                    )
                }
                is RequestResult.Error.Another -> {
                    error.throwable.printStackTrace()
                    return null
                }
            }
            return null
        }
    }

    override fun close() = dispose()
}
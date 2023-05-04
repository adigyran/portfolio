package com.aya.digital.core.mvi

import androidx.lifecycle.ViewModel
import com.aya.digital.core.data.base.dataprocessing.HttpCodeHandler
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.localisation.R.string as LocalisationR
import com.aya.digital.core.data.base.dataprocessing.HttpResponseCode
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import org.orbitmvi.orbit.ContainerHost
import timber.log.Timber

abstract class BaseViewModel<STATE : BaseState, SideEffect : BaseSideEffect> : ViewModel(),
    ContainerHost<STATE, SideEffect> {

    protected fun processError(error: RequestResultModel.Error,vararg httpHttpCodeHandlers: HttpCodeHandler) = processErrorGetSideEffect(error = error, httpHttpCodeHandlers = httpHttpCodeHandlers)?.let { postErrorSideEffect(it) }


    sealed class ErrorSideEffect : BaseSideEffect {
        data class Message(val msg: String) : ErrorSideEffect()
        data class MessageResource(val msgResource:Int): ErrorSideEffect()
    }

    companion object {
        internal fun processErrorGetSideEffect(
            error: RequestResultModel.Error,
            vararg httpHttpCodeHandlers: HttpCodeHandler,
        ): ErrorSideEffect? {
            Timber.d(error.toString())
            when (error) {
                is RequestResultModel.Error.Another -> {
                    error.throwable.printStackTrace()
                    val errorMessage = ErrorSideEffect.MessageResource(LocalisationR.unknown_error)
                    Firebase.crashlytics.recordException(error.throwable)
                    return errorMessage
                }
                is RequestResultModel.Error.HttpCode400 -> {
                   if (httpHttpCodeHandlers.find { it.first == HttpResponseCode.CODE_400 }?.second?.invoke(
                            error.errorList
                        ) != true
                    ) {
                        return when {
                            error.errorList.isNotEmpty() -> {
                                val errorMessage = ErrorSideEffect.Message(
                                    error.errorList.first().getDetail()
                                )
                                Firebase.crashlytics.log("400 error ${errorMessage.msg}")
                                errorMessage
                            }
                            else -> {
                                val errorMessage = ErrorSideEffect.MessageResource(LocalisationR.server_error)
                                Firebase.crashlytics.log("400 error ")
                                errorMessage
                            }
                        }
                    }
                    return null
                }
                RequestResultModel.Error.HttpCode401 -> {
                    val errorMessage = ErrorSideEffect.MessageResource(LocalisationR.server_error)
                    Firebase.crashlytics.log("401 error ")
                    return errorMessage
                }
                RequestResultModel.Error.HttpCode403 -> {
                    val errorMessage = ErrorSideEffect.MessageResource(LocalisationR.server_error)
                    Firebase.crashlytics.log("403 error ")
                    return errorMessage
                }
                RequestResultModel.Error.HttpCode404 -> {
                    val errorMessage = ErrorSideEffect.MessageResource(LocalisationR.server_error)
                    Firebase.crashlytics.log("404 error ")
                    return errorMessage
                }
                is RequestResultModel.Error.HttpCode406 -> {
                    val errorMessage = ErrorSideEffect.MessageResource(LocalisationR.server_error)
                    Firebase.crashlytics.log("406 error ")
                    return errorMessage
                }
                is RequestResultModel.Error.HttpCode409 -> {
                    if (httpHttpCodeHandlers.find { it.first == HttpResponseCode.CODE_409 }?.second?.invoke(
                            error.errorList
                        ) != true
                    ) {
                        return when {
                            error.errorList.isNotEmpty() -> {
                                val errorMessage = ErrorSideEffect.Message(
                                    error.errorList.first().getDetail()
                                )
                                Firebase.crashlytics.log("409 error ${errorMessage.msg}")
                                errorMessage
                            }
                            else -> {
                                val errorMessage = ErrorSideEffect.MessageResource(LocalisationR.server_error)
                                Firebase.crashlytics.log("409 error ")
                                errorMessage
                            }
                        }
                    }
                    return null
                }
                RequestResultModel.Error.HttpCodeAnother -> {
                    val errorMessage = ErrorSideEffect.MessageResource(LocalisationR.server_error)
                    Firebase.crashlytics.log("unknown code error ")
                    return errorMessage
                }
                is RequestResultModel.Error.JsonParsingError -> {
                    error.throwable.printStackTrace()
                    val errorMessage = ErrorSideEffect.MessageResource(LocalisationR.server_error)
                    Firebase.crashlytics.recordException(error.throwable)
                    return errorMessage
                }
                RequestResultModel.Error.NoRoleDefined -> {
                    val errorMessage = ErrorSideEffect.MessageResource(LocalisationR.no_role_defined_error)
                    Firebase.crashlytics.log("NoRoleDefined error ")
                    return errorMessage
                }
                RequestResultModel.Error.SocketTimeout -> {
                    val errorMessage = ErrorSideEffect.MessageResource(LocalisationR.socket_timeout_error)
                    Firebase.crashlytics.log("SocketTimeout error ")
                    return errorMessage
                }
                RequestResultModel.Error.UnknownHost -> {
                    val errorMessage = ErrorSideEffect.MessageResource(LocalisationR.unknown_host_error)
                    Firebase.crashlytics.log("UnknownHost error")
                    return errorMessage
                }
            }
            return null
        }


    }

    abstract fun onBack()

    protected fun sendExceptionToCrashlytics(throwable: Throwable)
    {
        Firebase.crashlytics.recordException(throwable)
    }

    protected open fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = Unit
}
package com.aya.digital.core.feature.auth.restorepassword.viewmodel

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.result.models.auth.PasswordRestoreResultModel
import com.aya.digital.core.data.base.result.models.code.CodeResultModel
import com.aya.digital.core.domain.auth.restorepassword.RestorePasswordSendCodeGetUserKeyUseCase
import com.aya.digital.core.domain.auth.restorepassword.*
import com.aya.digital.core.domain.auth.restorepassword.model.RestoreCodeResult
import com.aya.digital.core.domain.auth.restorepassword.model.RestorePasswordChangePasswordModel
import com.aya.digital.core.domain.auth.restorepassword.model.RestorePasswordGetCodeModel
import com.aya.digital.core.domain.auth.restorepassword.model.RestorePasswordSendCodeModel
import com.aya.digital.core.feature.auth.restorepassword.FieldsTags
import com.aya.digital.core.feature.auth.restorepassword.navigation.RestorePasswordNavigationEvents
import com.aya.digital.core.feature.auth.restorepassword.navigation.RestorePasswordOperationStateParam
import com.aya.digital.core.feature.auth.restorepassword.ui.RestorePasswordView
import com.aya.digital.core.feature.auth.restorepassword.viewmodel.model.RestorePasswordOperationState
import com.aya.digital.core.localisation.R
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.ui.base.validation.MailValidator
import com.aya.digital.core.ui.base.validation.NotEmptyValidator
import com.aya.digital.core.ui.base.validation.PasswordRepeatValidator
import com.aya.digital.core.ui.base.validation.PasswordValidator
import com.aya.digital.core.ui.base.validation.ValidationResult
import com.aya.digital.core.ui.base.validation.ValidationResult.Ok.processResultMessage
import com.aya.digital.core.ui.base.validation.validateField
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

internal class RestorePasswordViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val rootCoordinatorRouter: CoordinatorRouter,
    private val param: RestorePasswordView.Param,
    private val restorePasswordGetCodeUseCase: RestorePasswordGetCodeUseCase,
    private val restorePasswordSendCodeUseCase: RestorePasswordSendCodeGetUserKeyUseCase,
    private val changePasswordUseCase: RestorePasswordChangePasswordUseCase
) :
    BaseViewModel<RestorePasswordState, RestorePasswordSideEffects>() {
    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(RestorePasswordSideEffects.Error(errorSideEffect))
    }

    override val container = container<RestorePasswordState, RestorePasswordSideEffects>(
        initialState = RestorePasswordState(operationState = param.operationState.getInitialOperationState()),
    )
    {

    }
    private val emailValidator = MailValidator(R.string.no_role_defined_error)
    private val notEmptyValidator = NotEmptyValidator(R.string.no_role_defined_error)
    private val passwordValidator = PasswordValidator(R.string.no_role_defined_error)
    private val passwordRepeatValidator = PasswordRepeatValidator(R.string.no_role_defined_error)
    fun emailFieldChanging(tag: Int, text: String) = intent {
        reduce { state.copy(email = text, emailError = null) }
    }

    fun passwordFieldsChanging(tag: Int, text: String) = intent {
        when (tag) {
            FieldsTags.NEW_PASSWORD_FIELD_TAG -> {
                reduce { state.copy(passwordNew = text) }
            }

            FieldsTags.NEW_PASSWORD_REPEAT_FIELD_TAG -> {
                reduce { state.copy(passwordNewRepeat = text) }
            }
        }
    }

    private fun RestorePasswordOperationStateParam.getInitialOperationState(): RestorePasswordOperationState =
        when (this) {
            RestorePasswordOperationStateParam.ChangeTempPass -> RestorePasswordOperationState.ChangeTempPassword
            RestorePasswordOperationStateParam.RestorePassword -> RestorePasswordOperationState.RestoringEmailInput
        }

    fun saveButtonClicked() = intent {
        validateAllFields {
            if (!it) return@validateAllFields
            when (state.operationState) {
                RestorePasswordOperationState.ChangeTempPassword -> savePassword()
                RestorePasswordOperationState.RestoringChangePassword -> savePassword()
                RestorePasswordOperationState.RestoringEmailInput -> requestCode()
            }
        }

    }

    private fun codeEntered(code: String) = intent {
        reduce {
            state.copy(code = code)
        }
        if (code.length == 6) verifyCode()

    }

    private fun verifyCode() = intent {
        val verifyCodeResult =
            restorePasswordSendCodeUseCase(RestorePasswordSendCodeModel(state.code ?: "")).await()
        verifyCodeResult.processResult({ result ->
            when (result) {
                RestoreCodeResult.Error -> {
                    reenterCode()
                }

                is RestoreCodeResult.Success -> {
                    reduce {
                        state.copy(
                            operationState = RestorePasswordOperationState.RestoringChangePassword,
                            userKey = result.userKey
                        )
                    }
                }
            }
        }, {
            if (it == RequestResultModel.Error.HttpCode400(listOf())) reenterCode() else processError(
                it
            )
        })
    }

    private fun reenterCode() {
        requestCodeScreen()
    }

    private fun savePassword() = intent {
        state.userKey?.let { userKey ->
            changePasswordUseCase(
                RestorePasswordChangePasswordModel(
                    userKey,
                    state.passwordNew,
                    state.passwordNewRepeat
                )
            )
                .await()
                .processResult({
                    exitWithResult(true)
                }, {
                    processError(it)
                    exitWithResult(false)
                })

        }
    }

    private fun exitWithResult(result: Boolean) {
        coordinatorRouter.sendEvent(
            RestorePasswordNavigationEvents.FinishWithResult(
                param.requestCode,
                PasswordRestoreResultModel(result)
            )
        )
    }

    private fun requestCode() = intent {
        restorePasswordGetCodeUseCase(RestorePasswordGetCodeModel(state.email))
            .await()
            .processResult({
                requestCodeScreen()
            }, { processError(it) })
    }

    private fun requestCodeScreen() = intent {
        listenForCodeEvent()
        coordinatorRouter.sendEvent(
            RestorePasswordNavigationEvents.EnterCode(
                RequestCodes.CODE_INPUT_REQUEST_CODE_EMAIL,
                state.email
            )
        )
    }

    private fun listenForCodeEvent() {
        rootCoordinatorRouter.setResultListener(RequestCodes.CODE_INPUT_REQUEST_CODE_EMAIL) {
            if (it is CodeResultModel) {
                codeEntered(it.code)
            }
        }
    }

    private fun postErrorSideEffect() = intent {

    }

    private fun String.validateEmail() =
        this.validateField(false, notEmptyValidator, emailValidator)

    private fun String.validatePassword() =
        this.validateField(false, notEmptyValidator, passwordValidator)

    private fun Pair<String, String>.validateRepeatPassword() =
        this.validateField(false, passwordRepeatValidator)

    private fun validateAllFields(callback: (Boolean) -> Unit) =
        intent {
            when (state.operationState) {
                RestorePasswordOperationState.RestoringEmailInput -> {
                    val emailValidation = state.email.validateEmail()
                    reduce { state.copy(emailError = emailValidation.processResultMessage()) }
                    callback(emailValidation.isOk())
                }

                else -> {
                    val newPasswordValidation = state.passwordNew.validatePassword()
                    val newPasswordRepeatValidation =
                        Pair(state.passwordNew, state.passwordNewRepeat).validateRepeatPassword()
                    reduce {
                        state.copy(
                            passwordNewError = newPasswordValidation.processResultMessage(),
                            passwordNewErrorRepeat = newPasswordRepeatValidation.processResultMessage()
                        )
                    }
                    callback(newPasswordValidation.isOk() && newPasswordRepeatValidation.isOk())
                }
            }
        }



    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }

}


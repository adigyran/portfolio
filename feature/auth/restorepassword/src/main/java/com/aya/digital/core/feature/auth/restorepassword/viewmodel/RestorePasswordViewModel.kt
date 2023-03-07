package com.aya.digital.core.feature.auth.restorepassword.viewmodel

import com.aya.digital.core.data.base.result.models.auth.PasswordRestoreResultModel
import com.aya.digital.core.data.base.result.models.code.CodeResultModel
import com.aya.digital.core.domain.auth.RestorePasswordChangePasswordUseCase
import com.aya.digital.core.domain.auth.RestorePasswordGetCodeUseCase
import com.aya.digital.core.domain.auth.RestorePasswordSendCodeUseCase
import com.aya.digital.core.domain.auth.model.RestorePasswordGetCodeModel
import com.aya.digital.core.domain.auth.model.RestorePasswordSendCodeModel
import com.aya.digital.core.domain.auth.model.VerifyCodeResult
import com.aya.digital.core.feature.auth.restorepassword.navigation.RestorePasswordNavigationEvents
import com.aya.digital.core.feature.auth.restorepassword.ui.RestorePasswordView
import com.aya.digital.core.feature.auth.restorepassword.viewmodel.mode.RestorePasswordOperationState
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class RestorePasswordViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val rootCoordinatorRouter: CoordinatorRouter,
    private val param: RestorePasswordView.Param,
    private val restorePasswordGetCodeUseCase: RestorePasswordGetCodeUseCase,
    private val restorePasswordSendCodeUseCase: RestorePasswordSendCodeUseCase,
    private val changePasswordUseCase: RestorePasswordChangePasswordUseCase
) :
    BaseViewModel<RestorePasswordState, BaseSideEffect>() {
    override val container = container<RestorePasswordState, BaseSideEffect>(
        initialState = RestorePasswordState(operationState = param.operationState.getInitialOperationState()),
    )
    {

    }

    private fun RestorePasswordView.Param.RestorePasswordOperationStateParam.getInitialOperationState(): RestorePasswordOperationState =
        when (this) {
            RestorePasswordView.Param.RestorePasswordOperationStateParam.ChangeTempPass -> com.aya.digital.core.feature.auth.restorepassword.viewmodel.mode.RestorePasswordOperationState.ChangeTempPassword
            RestorePasswordView.Param.RestorePasswordOperationStateParam.RestorePassword -> com.aya.digital.core.feature.auth.restorepassword.viewmodel.mode.RestorePasswordOperationState.RestoringEmailInput
        }

    fun saveButtonClicked() = intent {
        when (state.operationState) {
            RestorePasswordOperationState.ChangeTempPassword -> savePassword()
            RestorePasswordOperationState.RestoringChangePassword -> savePassword()
            RestorePasswordOperationState.RestoringEmailInput -> requestCode()
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
        verifyCodeResult.processResult({
            when (it) {
                VerifyCodeResult.Error -> {
                    reenterCode()
                }
                VerifyCodeResult.Success -> {
                    reduce { state.copy(
                        operationState = RestorePasswordOperationState.RestoringChangePassword
                    ) }
                }
            }
        }, {

            Timber.d(it.toString())
        })
    }

    private fun reenterCode() {
        requestCodeScreen()
    }

    private fun savePassword() = intent {
        coordinatorRouter.sendEvent(
            RestorePasswordNavigationEvents.FinishWithResult(
                param.requestCode,
                PasswordRestoreResultModel(true)
            )
        )
    }

    private fun requestCode() = intent {
        restorePasswordGetCodeUseCase(RestorePasswordGetCodeModel(state.email))
            .await()
            .processResult({
                requestCodeScreen()
            }, { Timber.d(it.toString()) })
    }

    private fun requestCodeScreen() = intent {
        listenForCodeEvent()
        coordinatorRouter.sendEvent(
            RestorePasswordNavigationEvents.EnterCode(
                RequestCodes.CODE_INPUT_REQUEST_CODE,
                state.email
            )
        )
    }

    private fun listenForCodeEvent() {
        rootCoordinatorRouter.setResultListener(RequestCodes.CODE_INPUT_REQUEST_CODE) {
            if (it is CodeResultModel) {
                codeEntered(it.code)
            }
        }
    }
}


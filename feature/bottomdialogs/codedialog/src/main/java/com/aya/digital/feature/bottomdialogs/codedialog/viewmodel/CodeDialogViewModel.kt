package com.aya.digital.feature.bottomdialogs.codedialog.viewmodel

import com.aya.digital.core.data.base.result.models.code.CodeResultModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import com.aya.digital.feature.bottomdialogs.codedialog.CodeDialogInputState
import com.aya.digital.feature.bottomdialogs.codedialog.navigation.CodeDialogNavigationEvents
import com.aya.digital.feature.bottomdialogs.codedialog.ui.CodeDialogView
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CodeDialogViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val param: CodeDialogView.Param

) :
    BaseViewModel<CodeDialogState, BaseSideEffect>() {
    override val container = container<CodeDialogState, BaseSideEffect>(
        initialState = CodeDialogState(
            state = when (param.requestCode) {
                RequestCodes.CODE_INPUT_REQUEST_CODE_EMAIL -> CodeDialogInputState.ConfirmEmail(
                    email = param.value
                )
                RequestCodes.CODE_INPUT_REQUEST_CODE_PHONE -> CodeDialogInputState.ConfirmPhone(
                    phone = param.value
                )
                else -> CodeDialogInputState.ConfirmEmail(
                    email = param.value
                )
            },
            code = ""
        ),
    )
    {

    }

    fun codeChanged(newCode: String) = intent {
        reduce {
            state.copy(code = newCode)
        }
    }

    fun close() = intent {
        coordinatorRouter.sendEvent(CodeDialogNavigationEvents.Exit)
    }

    fun sendCode() = intent {
        coordinatorRouter.sendEvent(
            CodeDialogNavigationEvents.FinishWithResult(
                param.requestCode,
                CodeResultModel(state.code)
            )
        )
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) {
        TODO("Not yet implemented")
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }
}


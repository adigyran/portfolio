package com.aya.digital.feature.bottomdialogs.codedialog.viewmodel

import com.aya.digital.core.data.base.result.models.code.CodeResultModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
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

    override val container = container<CodeDialogState, BaseSideEffect>(
        initialState = CodeDialogState(
            email = param.email,
            code = ""
        ),
    )
    {

    }
}


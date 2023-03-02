package com.aya.digital.feature.bottomdialogs.codedialog.ui.model

import android.content.Context
import com.aya.digital.core.mvi.BaseStateTransformer

import com.aya.digital.feature.bottomdialogs.codedialog.viewmodel.CodeDialogState

class CodeDialogStateTransformer(context : Context): BaseStateTransformer<CodeDialogState, CodeDialogUiModel>() {
    override fun invoke(state: CodeDialogState): CodeDialogUiModel =
        CodeDialogUiModel(
            title = "Enter the code",
            code = state.code,
            bottomDescription = "Copy the code from your mailbox and paste it here ",
            description = "To confirm the email, enter the code we sent to: %s".format(state.email)
        )


}
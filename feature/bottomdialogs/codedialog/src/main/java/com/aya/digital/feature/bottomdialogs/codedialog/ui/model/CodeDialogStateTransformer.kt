package com.aya.digital.feature.bottomdialogs.codedialog.ui.model

import android.content.Context
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.base.masks.CommonMasks
import com.aya.digital.feature.bottomdialogs.codedialog.CodeDialogInputState

import com.aya.digital.feature.bottomdialogs.codedialog.viewmodel.CodeDialogState
import ru.tinkoff.decoro.MaskImpl

class CodeDialogStateTransformer(context : Context): BaseStateTransformer<CodeDialogState, CodeDialogUiModel>() {
    override fun invoke(state: CodeDialogState): CodeDialogUiModel =
        CodeDialogUiModel(
            title = "Enter the code",
            code = state.code,
            bottomDescription = state.state.getBottomDescription(),
            description = state.state.getDescription()
        )


    private fun CodeDialogInputState.getDescription() = when(this)
    {
        is CodeDialogInputState.ConfirmEmail -> "To confirm the email, enter the code we sent to: %s".format(this.email)
        is CodeDialogInputState.ConfirmPhone ->"To confirm the phone, enter the code we sent to: %s".format(this.phone.getMaskedText(CommonMasks.getUsPhoneValidator()))
    }
    private fun CodeDialogInputState.getBottomDescription() = when(this)
    {
        is CodeDialogInputState.ConfirmEmail -> "Copy the code from your mailbox and paste it here"
        is CodeDialogInputState.ConfirmPhone -> "Copy the code from sms and paste it here"
    }

    private fun String?.getMaskedText(mask: MaskImpl) = this?.let {
        mask.insertFront(it)
        mask.toString()
    }
}
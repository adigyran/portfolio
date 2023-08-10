package com.aya.digital.core.feature.auth.restorepassword.ui.model

import android.content.Context
import com.aya.digital.core.feature.auth.restorepassword.FieldsTags
import com.aya.digital.core.feature.auth.restorepassword.viewmodel.RestorePasswordState
import com.aya.digital.core.feature.auth.restorepassword.viewmodel.model.RestorePasswordOperationState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.emailphone.model.EmailPhoneFieldMode
import com.aya.digital.core.ui.delegates.components.fields.emailphone.model.EmailPhoneFieldUIModel
import com.aya.digital.core.ui.delegates.components.fields.password.model.PasswordFieldUIModel
import com.aya.digital.core.ui.delegates.components.labels.headline.model.HeadlineLabelUIModel

internal class RestorePasswordStateTransformer(private val context: Context) :
    BaseStateTransformer<RestorePasswordState, RestorePasswordUiModel>() {
    override fun invoke(state: RestorePasswordState): RestorePasswordUiModel =
        RestorePasswordUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    add(HeadlineLabelUIModel(labelText = state.operationState.getHeadlineText()))
                    addAll(state.operationState.getFields(state))
                }
            },
            buttonText = state.operationState.getButtonText(),
            buttonEnabled = state.emailError == null
        )

    private fun RestorePasswordOperationState.getButtonText() =
        when (this) {
            RestorePasswordOperationState.ChangeTempPassword -> "Save"
            RestorePasswordOperationState.RestoringChangePassword -> "Save Password"
            RestorePasswordOperationState.RestoringEmailInput -> "Next"
        }

    private fun RestorePasswordOperationState.getHeadlineText() =
        when (this) {
            RestorePasswordOperationState.ChangeTempPassword -> "Make up a new password and don't show it to anyone"
            RestorePasswordOperationState.RestoringChangePassword -> "Change password"
            RestorePasswordOperationState.RestoringEmailInput -> "Restore password"
        }

    private fun RestorePasswordOperationState.getFields(state: RestorePasswordState) =
        when (this) {
            RestorePasswordOperationState.RestoringEmailInput -> {
                listOf<DiffItem>(
                    EmailPhoneFieldUIModel(
                        tag = FieldsTags.EMAIL_PHONE_FIELD_TAG,
                        label = "Email",
                        state.email,
                        state.emailError,
                        mode = EmailPhoneFieldMode.EMAIL_MODE
                    )
                )
            }

            else -> {
                listOf<DiffItem>(
                    PasswordFieldUIModel(
                        tag = FieldsTags.NEW_PASSWORD_FIELD_TAG,
                        label = "New password",
                        state.passwordNew,
                        state.passwordNewError
                    ),
                    PasswordFieldUIModel(
                        tag = FieldsTags.NEW_PASSWORD_REPEAT_FIELD_TAG,
                        label = "Duplicate new password",
                        state.passwordNewRepeat,
                        state.passwordNewError
                    )
                )
            }
        }
}
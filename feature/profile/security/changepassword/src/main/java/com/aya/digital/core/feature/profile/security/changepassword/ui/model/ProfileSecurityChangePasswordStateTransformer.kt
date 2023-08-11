package com.aya.digital.core.feature.profile.security.changepassword.ui.model

import android.content.Context
import com.aya.digital.core.ext.strings
import com.aya.digital.core.feature.profile.security.changepassword.FieldsTags
import com.aya.digital.core.feature.profile.security.changepassword.viewmodel.ProfileSecurityChangePasswordState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.password.model.PasswordFieldUIModel
import com.aya.digital.core.ui.delegates.components.labels.headline.model.HeadlineTwoLineLabelUIModel

internal class ProfileSecurityChangePasswordStateTransformer(private val context: Context) :
    BaseStateTransformer<ProfileSecurityChangePasswordState, ProfileSecurityChangePasswordUiModel>() {
    override fun invoke(state: ProfileSecurityChangePasswordState): ProfileSecurityChangePasswordUiModel =
        ProfileSecurityChangePasswordUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    add(
                        HeadlineTwoLineLabelUIModel(
                            "Change password",
                            "You can change the login password"
                        )
                    )
                    add(
                        PasswordFieldUIModel(
                            tag = FieldsTags.CURRENT_PASSWORD_FIELD_TAG,
                            label = "Current password",
                            text = state.currentPassword,
                            error = state.currentPasswordError?.let { context.strings[it] }
                        )
                    )
                    add(
                        PasswordFieldUIModel(
                            tag = FieldsTags.NEW_PASSWORD_FIELD_TAG,
                            label = "New password",
                            text = state.newPassword,
                            error = state.newPasswordError?.let { context.strings[it] }
                        )
                    )
                    add(
                        PasswordFieldUIModel(
                            tag = FieldsTags.NEW_REPEAT_PASSWORD_FIELD_TAG,
                            label = "Duplicate password",
                            text = state.newRepeatPassword,
                            error = state.newRepeatPasswordError?.let { context.strings[it] }
                        )
                    )
                }
            }
        )


}
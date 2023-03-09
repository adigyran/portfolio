package com.aya.digital.core.feature.profile.security.changepassword.ui.model

import android.content.Context
import com.aya.digital.core.feature.profile.security.changepassword.FieldsTags
import com.aya.digital.core.feature.profile.security.changepassword.viewmodel.ProfileSecurityChangePasswordState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.password.model.PasswordFieldUIModel
import com.aya.digital.core.ui.delegates.components.labels.headline.model.HeadlineTwoLineLabelUIModel

class ProfileSecurityChangePasswordStateTransformer(private val context: Context) :
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
                            FieldsTags.CURRENT_PASSWORD_FIELD_TAG,
                            "Current password",
                            state.currentPassword,
                            state.currentPasswordError
                        )
                    )
                    add(
                        PasswordFieldUIModel(
                            FieldsTags.NEW_PASSWORD_FIELD_TAG,
                            "New password",
                            state.newPassword,
                            state.newPasswordError
                        )
                    )
                    add(
                        PasswordFieldUIModel(
                            FieldsTags.NEW_REPEAT_PASSWORD_FIELD_TAG,
                            "Duplicate password",
                            state.newRepeatPassword,
                            state.newRepeatPasswordError
                        )
                    )
                }
            }
        )


}
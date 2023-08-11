package com.aya.digital.core.feature.profile.security.changeemail.ui.model

import android.content.Context
import com.aya.digital.core.ext.strings
import com.aya.digital.core.feature.profile.security.changeemail.FieldsTags
import com.aya.digital.core.feature.profile.security.changeemail.viewmodel.ProfileSecurityChangeEmailState
import com.aya.digital.core.localisation.R
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.base.validation.MailValidator
import com.aya.digital.core.ui.base.validation.NotEmptyValidator
import com.aya.digital.core.ui.base.validation.ValidationResult
import com.aya.digital.core.ui.base.validation.validateField
import com.aya.digital.core.ui.delegates.components.fields.emailphone.model.EmailFieldUIModel
import com.aya.digital.core.ui.delegates.components.fields.emailphone.model.PhoneFieldUIModel
import com.aya.digital.core.ui.delegates.components.labels.headline.model.HeadlineTwoLineLabelUIModel

internal class ProfileSecurityChangeEmailStateTransformer(private val context: Context) :
    BaseStateTransformer<ProfileSecurityChangeEmailState, ProfileSecurityChangeEmailUiModel>() {
    override fun invoke(state: ProfileSecurityChangeEmailState): ProfileSecurityChangeEmailUiModel =
        ProfileSecurityChangeEmailUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    add(
                        HeadlineTwoLineLabelUIModel(
                            "Change Email",
                            "You can use your email to log in"
                        )
                    )
                    add(
                        EmailFieldUIModel(
                            tag = FieldsTags.EMAIL_PHONE_FIELD_TAG,
                            label = "Email",
                            text = state.email,
                            error = state.emailError?.let { context.strings[it] }
                        )
                    )
                }
            },
            buttonEnabled = true
        )
}
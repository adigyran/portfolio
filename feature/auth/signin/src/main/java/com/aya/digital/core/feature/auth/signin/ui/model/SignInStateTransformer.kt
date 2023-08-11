package com.aya.digital.core.feature.auth.signin.ui.model

import android.content.Context
import com.aya.digital.core.feature.auth.signin.FieldsTags
import com.aya.digital.core.feature.auth.signin.viewmodel.SignInState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.emailphone.model.EmailFieldUIModel
import com.aya.digital.core.ui.delegates.components.fields.password.model.PasswordFieldUIModel
import com.aya.digital.core.ui.delegates.components.labels.headline.model.HeadlineLabelUIModel
import com.aya.digital.core.ui.delegates.components.labels.spannablehelper.model.SpannableHelperLabelUIModel

class SignInStateTransformer(private val context: Context) :
    BaseStateTransformer<SignInState, SignInUiModel>() {
    override fun invoke(state: SignInState): SignInUiModel =
        SignInUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    add(HeadlineLabelUIModel("Sign In"))
                    add(
                        EmailFieldUIModel(
                            tag = FieldsTags.EMAIL_PHONE_FIELD_TAG,
                            label = "Email or Phone",
                            text = state.email,
                            error = state.emailError
                        )
                    )
                    add(
                        PasswordFieldUIModel(
                            tag = FieldsTags.PASSWORD_FIELD_TAG,
                            label = "Password",
                            text = state.password,
                            error = state.passwordError
                        )
                    )
                    add(
                        SpannableHelperLabelUIModel(
                            formattedText = "Forgot password? %s",
                            spannableText = "Restore"
                        )
                    )
                }
            }
        )


}
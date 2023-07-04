package com.aya.digital.feature.auth.signup.ui.model

import android.content.Context
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.emailphone.model.EmailPhoneFieldUIModel
import com.aya.digital.core.ui.delegates.components.fields.name.model.NameFieldUIModel
import com.aya.digital.core.ui.delegates.components.fields.password.model.PasswordFieldUIModel
import com.aya.digital.core.ui.delegates.components.fields.selection.model.SelectionFieldUIModel
import com.aya.digital.core.ui.delegates.components.labels.headline.model.HeadlineLabelUIModel
import com.aya.digital.feature.auth.signup.FieldsTags
import com.aya.digital.feature.auth.signup.viewmodel.SignUpState

class SignUpStateTransformer(context: Context) :
    BaseStateTransformer<SignUpState, SignUpUiModel>() {
    override fun invoke(state: SignUpState): SignUpUiModel =
        SignUpUiModel(
            data = mutableListOf<DiffItem>().apply {
                add(HeadlineLabelUIModel("Sign Up"))
                add(
                    NameFieldUIModel(
                        tag =  FieldsTags.FIRST_NAME_FIELD_TAG,
                        label = "First Name",
                        text = state.firstName,
                        error = state.firstNameError
                    )
                )
                add(
                    NameFieldUIModel(
                        FieldsTags.LAST_NAME_FIELD_TAG,
                        "Last Name",
                        state.lastName,
                        state.lastNameError
                    )
                )
                add(EmailPhoneFieldUIModel( tag =  FieldsTags.EMAIL_PHONE_FIELD_TAG,"Email or Phone", state.email, state.emailError))
                add(
                    SelectionFieldUIModel(
                        FieldsTags.INSURANCE_COMPANY_SELECT_FIELD_TAG,
                        "Insurance",
                        state.insurances.map {
                            it.name
                        }.joinToString(", "),
                        null
                    )
                )
                add(
                    PasswordFieldUIModel(
                        FieldsTags.PASSWORD_FIELD_TAG,
                        "Password",
                        state.password,
                        state.passwordError
                    )
                )
                add(
                    PasswordFieldUIModel(
                        FieldsTags.PASSWORD_CONFIRM_FIELD_TAG,
                        "Duplicate password",
                        state.confirmPassword,
                        state.confirmPasswordError
                    )
                )
            }
        )


}
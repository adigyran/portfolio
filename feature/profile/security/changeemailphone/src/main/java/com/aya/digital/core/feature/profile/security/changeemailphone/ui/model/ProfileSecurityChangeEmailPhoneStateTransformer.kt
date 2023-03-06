package com.aya.digital.core.feature.profile.security.changeemailphone.ui.model

import android.content.Context
import com.aya.digital.core.feature.profile.security.changeemailphone.viewmodel.ProfileSecurityChangeEmailPhoneState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.emailphone.model.EmailPhoneFieldUIModel

class ProfileSecurityChangeEmailPhoneStateTransformer(context : Context): BaseStateTransformer<ProfileSecurityChangeEmailPhoneState, ProfileSecurityChangeEmailPhoneUiModel>() {
    override fun invoke(state: ProfileSecurityChangeEmailPhoneState): ProfileSecurityChangeEmailPhoneUiModel =
        ProfileSecurityChangeEmailPhoneUiModel(
            data = kotlin.run {
               return@run mutableListOf<DiffItem>().apply {
                   add(EmailPhoneFieldUIModel("Email",state.email,state.emailError))
                }
            }
        )


}
package com.aya.digital.core.feature.profile.security.changepassword.ui.model

import android.content.Context
import com.aya.digital.core.feature.profile.security.changepassword.viewmodel.ProfileSecurityChangePasswordState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem

class ProfileSecurityChangePasswordStateTransformer(context : Context): BaseStateTransformer<ProfileSecurityChangePasswordState, ProfileSecurityChangePasswordUiModel>() {
    override fun invoke(state: ProfileSecurityChangePasswordState): ProfileSecurityChangePasswordUiModel =
        ProfileSecurityChangePasswordUiModel(
            data = kotlin.run {
               return@run mutableListOf<DiffItem>().apply {

                }
            }
        )


}
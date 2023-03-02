package com.aya.digital.core.feature.profile.emergencycontact.ui.model

import android.content.Context
import com.aya.digital.core.feature.profile.emergencycontact.viewmodel.ProfileEmergencyContactState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem


class ProfileEmergencyContactStateTransformer(context : Context): BaseStateTransformer<ProfileEmergencyContactState, ProfileEmergencyContactUiModel>() {
    override fun invoke(state: ProfileEmergencyContactState): ProfileEmergencyContactUiModel =
        ProfileEmergencyContactUiModel(
            data = kotlin.run {
               return@run mutableListOf<DiffItem>().apply {

                }
            }
        )


}
package com.aya.digital.core.feature.profile.clinicinfo.ui.model

import android.content.Context
import com.aya.digital.core.feature.profile.clinicinfo.viewmodel.ProfileClinicInfoState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem

class ProfileClinicInfoStateTransformer(context : Context): BaseStateTransformer<ProfileClinicInfoState, ProfileClinicInfoUiModel>() {
    override fun invoke(state: ProfileClinicInfoState): ProfileClinicInfoUiModel =
        ProfileClinicInfoUiModel(
            data = kotlin.run {
               return@run mutableListOf<DiffItem>().apply {

                }
            }
        )


}
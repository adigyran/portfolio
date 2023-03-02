package com.aya.digital.core.feature.profile.security.securitysummary.ui.model

import android.content.Context
import com.aya.digital.core.feature.profile.security.securitysummary.viewmodel.ProfileSecuritySummaryState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem

class ProfileSecuritySummaryStateTransformer(context : Context): BaseStateTransformer<ProfileSecuritySummaryState, ProfileSecuritySummaryUiModel>() {
    override fun invoke(state: ProfileSecuritySummaryState): ProfileSecuritySummaryUiModel =
        ProfileSecuritySummaryUiModel(
            data = kotlin.run {
               return@run mutableListOf<DiffItem>().apply {

                }
            }
        )


}
package com.aya.digital.core.feature.profile.generalinfo.view.ui.model

import android.content.Context
import com.aya.digital.core.feature.profile.generalinfo.view.FieldsTags
import com.aya.digital.core.feature.profile.generalinfo.view.viewmodel.ProfileGeneralInfoViewState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.base.ext.SpannableObject

class ProfileGeneralInfoViewStateTransformer(context : Context): BaseStateTransformer<ProfileGeneralInfoViewState, ProfileGeneralInfoViewUiModel>() {
    override fun invoke(state: ProfileGeneralInfoViewState): ProfileGeneralInfoViewUiModel =
        ProfileGeneralInfoViewUiModel(
            data = kotlin.run {
               return@run mutableListOf<DiffItem>().apply {

                }
            }
        )


}
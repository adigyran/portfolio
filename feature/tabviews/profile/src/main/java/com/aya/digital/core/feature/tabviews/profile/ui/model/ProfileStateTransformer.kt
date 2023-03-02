package com.aya.digital.core.feature.tabviews.profile.ui.model

import android.content.Context
import com.aya.digital.core.feature.tabviews.profile.viewmodel.ProfileState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem


class ProfileStateTransformer(context : Context): BaseStateTransformer<ProfileState, ProfileUiModel>() {
    override fun invoke(state: ProfileState): ProfileUiModel =
        ProfileUiModel(
            data = kotlin.run {
               return@run mutableListOf<DiffItem>().apply {

                }
            }
        )


}
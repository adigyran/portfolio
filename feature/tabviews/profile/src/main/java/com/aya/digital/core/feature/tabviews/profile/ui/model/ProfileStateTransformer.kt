package com.aya.digital.core.feature.tabviews.profile.ui.model

import android.content.Context
import com.aya.digital.core.baseresources.R
import com.aya.digital.core.feature.tabviews.profile.FieldsTags
import com.aya.digital.core.feature.tabviews.profile.viewmodel.ProfileState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.profile.info.model.ProfileMainUIModel


class ProfileStateTransformer(context: Context) :
    BaseStateTransformer<ProfileState, ProfileUiModel>() {
    override fun invoke(state: ProfileState): ProfileUiModel =
        ProfileUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    add(ProfileMainUIModel(FieldsTags.GENERAL_INFO_BUTTON_TAG,R.drawable.ic_profile_generalinfo,"General Info"))
                    add(ProfileMainUIModel(FieldsTags.EMERGENCY_CONTACT_BUTTON_TAG,R.drawable.ic_profile_emergency,"Emergency Contact"))
                    add(ProfileMainUIModel(FieldsTags.SECURITY_BUTTON_TAG,R.drawable.ic_profile_security,"Security"))
                    add(ProfileMainUIModel(FieldsTags.INSURANCE_BUTTON_TAG,R.drawable.ic_profile_insurance,"Insurance"))
                    add(ProfileMainUIModel(FieldsTags.NOTIFICATION_BUTTON_TAG,R.drawable.ic_profile_notification,"Notification"))
                }
            },
            avatar = state.avatar,
            address = state.address,
            name = kotlin.run {
                if (state.firstName == null || state.lastName == null) return@run null
                val lastNameInitial =
                    if (state.lastName.isNotBlank()) "%s.".format(state.lastName.first()) else ""
                return@run "%s %s".format(state.firstName, lastNameInitial)
            }
        )


}
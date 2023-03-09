package com.aya.digital.core.feature.profile.notifications.ui.model

import android.content.Context
import com.aya.digital.core.feature.profile.notifications.viewmodel.ProfileNotificationsState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem

class ProfileNotificationsStateTransformer(context: Context) :
    BaseStateTransformer<ProfileNotificationsState, ProfileNotificationsUiModel>() {
    override fun invoke(state: ProfileNotificationsState): ProfileNotificationsUiModel =
        ProfileNotificationsUiModel(
            emailNotificationsState = kotlin.run {
                return@run state.emailNotification
            }
        )


}
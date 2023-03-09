package com.aya.digital.core.feature.profile.generalinfo.view.navigation

import com.aya.digital.core.domain.profile.generalinfo.view.model.ProfileInfoModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfileGeneralInfoViewNavigationEvents : CoordinatorEvent() {
    data class EditProfile(val requestCode:String,val profileInfoModel: ProfileInfoModel?) : ProfileGeneralInfoViewNavigationEvents()
}
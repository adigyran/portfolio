package com.aya.digital.core.feature.profile.generalinfo.view.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfileGeneralInfoViewNavigationEvents : CoordinatorEvent() {
    object EditProfile : ProfileGeneralInfoViewNavigationEvents()
}
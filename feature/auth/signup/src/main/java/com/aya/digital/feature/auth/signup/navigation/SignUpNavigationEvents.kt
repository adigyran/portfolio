package com.aya.digital.feature.auth.signup.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class SignUpNavigationEvents : CoordinatorEvent() {
    object SignIn : SignUpNavigationEvents()
    data class SelectInsuranceCompanies(val requestCode:String, val selectedItems:Set<Int>) : SignUpNavigationEvents()
    data class EnterCode(val requestCode:String, val email:String) : SignUpNavigationEvents()


}
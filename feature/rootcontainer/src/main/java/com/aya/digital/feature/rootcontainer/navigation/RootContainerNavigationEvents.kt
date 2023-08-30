package com.aya.digital.feature.rootcontainer.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class RootContainerNavigationEvents : CoordinatorEvent() {
    object OpenDefaultScreen : RootContainerNavigationEvents()

    object OpenDefaultScreenAuthorized : RootContainerNavigationEvents()


    object OpenBottomNavigationScreenDefault : RootContainerNavigationEvents()
    data class SelectMultipleItems(val requestCode:String, val selectedItems:Set<Int>) : RootContainerNavigationEvents()
    data class SelectSingleItem(val requestCode:String, val selectedItem:Int?) : RootContainerNavigationEvents()


    data class EnterCode(val requestCode:String, val value:String) : RootContainerNavigationEvents()
    data class RestorePassword(val requestCode:String) : RootContainerNavigationEvents()
    data class ChangeTempPassword(val requestCode:String) : RootContainerNavigationEvents()

    data class OpenVideoCall(val roomId:Int):RootContainerNavigationEvents()


}
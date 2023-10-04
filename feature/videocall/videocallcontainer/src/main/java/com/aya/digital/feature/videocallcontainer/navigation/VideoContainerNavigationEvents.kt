package com.aya.digital.feature.videocallcontainer.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class VideoContainerNavigationEvents : CoordinatorEvent() {

    data class OpenVideoCall(val roomId:Int): VideoContainerNavigationEvents()


}
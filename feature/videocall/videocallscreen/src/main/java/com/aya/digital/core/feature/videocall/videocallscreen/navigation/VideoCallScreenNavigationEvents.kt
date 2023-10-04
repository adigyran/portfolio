package com.aya.digital.core.feature.videocall.videocallscreen.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class VideoCallScreenNavigationEvents : CoordinatorEvent()
{
    data class StartForegroundService(val roomName:String):VideoCallScreenNavigationEvents()

    object StopForegroundService : VideoCallScreenNavigationEvents()
    object Back : VideoCallScreenNavigationEvents()
}
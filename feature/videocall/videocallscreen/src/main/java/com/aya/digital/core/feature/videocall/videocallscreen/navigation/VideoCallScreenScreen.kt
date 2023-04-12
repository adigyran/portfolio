package com.aya.digital.core.feature.videocall.videocallscreen.navigation

import com.aya.digital.core.feature.videocall.videocallscreen.ui.VideoCallScreenView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

data class VideoCallScreenScreen(val doctorId: Int) :
    HealthAppFragmentScreen(fragmentCreator = { VideoCallScreenView.getNewInstance(doctorId = doctorId) })

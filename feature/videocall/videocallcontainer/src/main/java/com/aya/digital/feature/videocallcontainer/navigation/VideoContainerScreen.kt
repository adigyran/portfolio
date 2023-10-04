package com.aya.digital.feature.videocallcontainer.navigation

import android.content.Intent
import com.aya.digital.core.navigation.screen.HealthAppActivityScreen
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen
import com.aya.digital.feature.videocallcontainer.ui.VideoContainerView

data class VideoContainerScreen(val roomId: Int) :
    HealthAppActivityScreen(intentCreator = { VideoContainerView.getNewInstance(it,roomId) })

package com.aya.digital.core.feature.profile.notifications.navigation

import com.aya.digital.core.feature.profile.notifications.ui.ProfileNotificationsView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

object ProfileNotificationsScreen : HealthAppFragmentScreen(fragmentCreator = { ProfileNotificationsView() })

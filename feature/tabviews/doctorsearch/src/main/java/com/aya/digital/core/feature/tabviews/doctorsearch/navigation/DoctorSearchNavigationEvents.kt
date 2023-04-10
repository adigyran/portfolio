package com.aya.digital.core.feature.tabviews.doctorsearch.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class DoctorSearchNavigationEvents : CoordinatorEvent() {
    data class OpenDoctorCard(val doctorId: Int) : DoctorSearchNavigationEvents()
}
package com.aya.digital.core.feature.tabviews.doctorsearchcontainer.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class DoctorSearchContainerNavigationEvents : CoordinatorEvent() {
    object OpenDefaultScreen : DoctorSearchContainerNavigationEvents()
    data class OpenDoctorCard(val doctorId: Int) : DoctorSearchContainerNavigationEvents()
    data class SelectInsuranceCompanies(
        val requestCode: String,
        val selectedInsurancesIds: List<Int>?
    ) : DoctorSearchContainerNavigationEvents()

    data class SelectSpecialisations(
        val requestCode: String,
        val selectedSpecialisationsIds: List<Int>?
    ) : DoctorSearchContainerNavigationEvents()

    data class SelectLocation(val requestCode: String, val selectedLocation: Int?) :
        DoctorSearchContainerNavigationEvents()


}
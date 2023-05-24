package com.aya.digital.core.feature.tabviews.doctorsearch.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class DoctorSearchNavigationEvents : CoordinatorEvent() {
    object OpenDefaultScreen : DoctorSearchNavigationEvents()
    data class OpenDoctorCard(val doctorId: Int) : DoctorSearchNavigationEvents()
    data class SelectInsuranceCompanies(
        val requestCode: String,
        val selectedInsurancesIds: List<Int>?
    ) : DoctorSearchNavigationEvents()

    data class SelectSpecialisations(
        val requestCode: String,
        val selectedSpecialisationsIds: List<Int>?
    ) : DoctorSearchNavigationEvents()

    data class SelectLocation(val requestCode: String, val selectedLocation: Int) :
        DoctorSearchNavigationEvents()


}
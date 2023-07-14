package com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class DoctorSearchMapNavigationEvents : CoordinatorEvent() {
    object OpenDefaultScreen : DoctorSearchMapNavigationEvents()
    data class OpenDoctorCard(val doctorId: Int) : DoctorSearchMapNavigationEvents()
    data class SelectInsuranceCompanies(
        val requestCode: String,
        val selectedInsurancesIds: List<Int>?
    ) : DoctorSearchMapNavigationEvents()

    data class SelectSpecialisations(
        val requestCode: String,
        val selectedSpecialisationsIds: List<Int>?
    ) : DoctorSearchMapNavigationEvents()

    data class SelectLocation(val requestCode: String, val selectedLocation: Int?) :
        DoctorSearchMapNavigationEvents()


}
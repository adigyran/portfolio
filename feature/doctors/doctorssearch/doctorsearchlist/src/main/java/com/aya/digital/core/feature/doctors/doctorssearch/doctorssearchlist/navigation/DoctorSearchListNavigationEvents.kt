package com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class DoctorSearchListNavigationEvents : CoordinatorEvent() {
    object OpenDefaultScreen : DoctorSearchListNavigationEvents()
    data class OpenDoctorCard(val doctorId: Int) : DoctorSearchListNavigationEvents()
    data class SelectInsuranceCompanies(
        val requestCode: String,
        val selectedInsurancesIds: List<Int>?
    ) : DoctorSearchListNavigationEvents()

    data class SelectSpecialisations(
        val requestCode: String,
        val selectedSpecialisationsIds: List<Int>?
    ) : DoctorSearchListNavigationEvents()

    data class SelectLocation(val requestCode: String, val selectedLocation: Int?) :
        DoctorSearchListNavigationEvents()


}
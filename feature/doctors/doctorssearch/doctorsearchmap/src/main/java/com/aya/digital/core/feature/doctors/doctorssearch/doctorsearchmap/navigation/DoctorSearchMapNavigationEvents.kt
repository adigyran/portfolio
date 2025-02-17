package com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.navigation

import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class DoctorSearchMapNavigationEvents : CoordinatorEvent() {
    object OpenDefaultScreen : DoctorSearchMapNavigationEvents()
    data class OpenDoctorCard(val doctorId: Int) : DoctorSearchMapNavigationEvents()

    data class OpenDoctorsCluster( val requestCode: String,val doctors:List<DoctorModel>)  : DoctorSearchMapNavigationEvents()
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
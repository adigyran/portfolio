package com.aya.digital.core.feature.doctors.doctorcard.viewmodel

import com.aya.digital.core.domain.doctors.base.model.DoctorModel
import com.aya.digital.core.domain.schedule.base.model.ScheduleSlotModel
import com.aya.digital.core.feature.doctors.doctorcard.DoctorCardMode
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class DoctorCardState(
    val doctorCardMode: DoctorCardMode = DoctorCardMode.ShowingSlots,
    val doctorSlots:List<ScheduleSlotModel>? = null,
    val doctorFirstName: String? = null,
    val doctorLastName: String? = null,
    val doctorMiddleName: String? = null,
    val doctorAvatar: String? = null,
    val doctorBio: String? = null,
    val doctorClinics: List<DoctorModel.ClinicModel>? = null,
    val doctorLocation: DoctorModel.LocationModel? = null,
    val doctorAddress: String? = null,
    val doctorSpecialities: List<DoctorModel.SpecialityModel>? = null,
    val doctorInsurances:List<DoctorModel.InsuranceModel>? = null,
    val bioReadMore:Boolean = false
) : BaseState

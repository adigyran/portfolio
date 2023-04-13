package com.aya.digital.core.feature.doctors.doctorcard.ui.model

import android.content.Context
import com.aya.digital.core.feature.doctors.doctorcard.viewmodel.DoctorCardState
import com.aya.digital.core.mvi.BaseStateTransformer

class DoctorCardStateTransformer(context: Context) :
    BaseStateTransformer<DoctorCardState, DoctorCardUiModel>() {
    override fun invoke(state: DoctorCardState): DoctorCardUiModel =
        DoctorCardUiModel(
            doctorCardMode = state.doctorCardMode,
            doctorName = "Dr. %s".format(state.doctorLastName.getField()),
            doctorSpeciality = state.doctorSpecialities?.let { specialityModels -> specialityModels.firstOrNull()?.name?:getNotSpecifiedText() }?:getNotSpecifiedText(),
            doctorClinic = state.doctorClinics?.let { clinicModels -> clinicModels.firstOrNull()?.clinicName?:getNotSpecifiedText() }?:getNotSpecifiedText(),
            doctorAvatar = state.doctorAvatar,
            doctorAddress = state.doctorAddress.getField()
        )
    private fun getNotSpecifiedText() = "Not specified"
    private fun String?.getField() = this ?: getNotSpecifiedText()

}
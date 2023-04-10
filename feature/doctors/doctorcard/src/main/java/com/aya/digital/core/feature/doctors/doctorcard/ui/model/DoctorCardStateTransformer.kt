package com.aya.digital.core.feature.doctors.doctorcard.ui.model

import android.content.Context
import com.aya.digital.core.feature.doctors.doctorcard.viewmodel.DoctorCardState
import com.aya.digital.core.mvi.BaseStateTransformer

class DoctorCardStateTransformer(context: Context) :
    BaseStateTransformer<DoctorCardState, DoctorCardUiModel>() {
    override fun invoke(state: DoctorCardState): DoctorCardUiModel =
        DoctorCardUiModel(
            doctorName = ""
        )


}
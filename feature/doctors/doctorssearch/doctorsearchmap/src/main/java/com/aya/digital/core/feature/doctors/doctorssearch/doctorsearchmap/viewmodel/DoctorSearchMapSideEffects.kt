package com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.viewmodel

import android.location.Location
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class DoctorSearchMapSideEffects:BaseSideEffect {

    data class OnLocationChanged(val location:Location): DoctorSearchMapSideEffects()
    data class Error(val error:BaseViewModel.ErrorSideEffect) : DoctorSearchMapSideEffects()
}

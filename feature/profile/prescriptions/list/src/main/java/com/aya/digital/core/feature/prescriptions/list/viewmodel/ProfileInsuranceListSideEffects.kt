package com.aya.digital.core.feature.prescriptions.list.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class ProfileInsuranceListSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : ProfileInsuranceListSideEffects()
    data class ShowInsuranceActionsDialog(val insuranceId:Int) : ProfileInsuranceListSideEffects()
}

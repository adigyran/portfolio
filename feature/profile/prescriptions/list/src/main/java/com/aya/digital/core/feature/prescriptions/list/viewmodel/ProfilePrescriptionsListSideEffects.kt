package com.aya.digital.core.feature.prescriptions.list.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class ProfilePrescriptionsListSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : ProfilePrescriptionsListSideEffects()
    data class ShowPrescriptionsActionsDialog(val insuranceId:Int) : ProfilePrescriptionsListSideEffects()
}

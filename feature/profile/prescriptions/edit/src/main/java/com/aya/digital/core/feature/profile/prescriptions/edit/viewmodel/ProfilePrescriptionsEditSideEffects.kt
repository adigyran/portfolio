package com.aya.digital.core.feature.profile.prescriptions.edit.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class ProfilePrescriptionsEditSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : ProfilePrescriptionsEditSideEffects()
    object ShowInsuranceActionsDialog : ProfilePrescriptionsEditSideEffects()
    object SelectImage: ProfilePrescriptionsEditSideEffects()
    object ShowFullScreenPolicy: ProfilePrescriptionsEditSideEffects()

    object HideFullScreenPolicy: ProfilePrescriptionsEditSideEffects()
}

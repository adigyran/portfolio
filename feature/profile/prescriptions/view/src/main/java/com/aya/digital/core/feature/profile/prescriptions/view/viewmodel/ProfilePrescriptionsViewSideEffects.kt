package com.aya.digital.core.feature.profile.prescriptions.view.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class ProfilePrescriptionsViewSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : ProfilePrescriptionsViewSideEffects()
    object ShowInsuranceActionsDialog : ProfilePrescriptionsViewSideEffects()
    object SelectImage: ProfilePrescriptionsViewSideEffects()
    object ShowFullScreenPolicy: ProfilePrescriptionsViewSideEffects()

    object HideFullScreenPolicy: ProfilePrescriptionsViewSideEffects()
}

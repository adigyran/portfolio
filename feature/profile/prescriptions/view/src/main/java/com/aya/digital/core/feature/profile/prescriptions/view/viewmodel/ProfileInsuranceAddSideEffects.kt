package com.aya.digital.core.feature.profile.prescriptions.view.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class ProfileInsuranceAddSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : ProfileInsuranceAddSideEffects()
    object ShowInsuranceActionsDialog : ProfileInsuranceAddSideEffects()
    object SelectImage: ProfileInsuranceAddSideEffects()
    object ShowFullScreenPolicy: ProfileInsuranceAddSideEffects()

    object HideFullScreenPolicy: ProfileInsuranceAddSideEffects()
}

package com.aya.digital.core.feature.profile.generalinfo.view.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class ProfileGeneralInfoSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : ProfileGeneralInfoSideEffects()
}

package com.aya.digital.core.feature.tabviews.home.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class HomeSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : HomeSideEffects()
}

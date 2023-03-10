package com.aya.digital.core.feature.choosers.selectwithsearch.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel

sealed class SelectWithSearchChooserSideEffects:BaseSideEffect {
    data class Error(val error:BaseViewModel.ErrorSideEffect) : SelectWithSearchChooserSideEffects()
}

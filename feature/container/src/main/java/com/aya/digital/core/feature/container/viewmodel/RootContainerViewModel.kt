package com.aya.digital.core.feature.container.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container

class RootContainerViewModel() : BaseViewModel<RootContainerState, BaseSideEffect>() {
    override val container = container<RootContainerState, BaseSideEffect>(
        initialState = RootContainerState(),
    )
    {

    }


}
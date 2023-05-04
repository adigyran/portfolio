package com.aya.digital.feature.tabs.doctorsearch.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.orbitmvi.orbit.viewmodel.container

class DoctorSearchTabViewModel(val coordinatorRouter: CoordinatorRouter) :
    BaseViewModel<DoctorSearchTabState, BaseSideEffect>() {
    override fun onBack() {
        TODO("Not yet implemented")
    }

    override val container = container<DoctorSearchTabState, BaseSideEffect>(
        initialState = DoctorSearchTabState,
    )
    {

    }
}


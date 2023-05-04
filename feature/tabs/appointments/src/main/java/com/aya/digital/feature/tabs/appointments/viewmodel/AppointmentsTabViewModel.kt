package com.aya.digital.feature.tabs.appointments.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.orbitmvi.orbit.viewmodel.container

class AppointmentsTabViewModel(val coordinatorRouter: CoordinatorRouter) :
    BaseViewModel<AppointmentsTabState, BaseSideEffect>() {
    override fun onBack() {
        TODO("Not yet implemented")
    }

    override val container = container<AppointmentsTabState, BaseSideEffect>(
        initialState = AppointmentsTabState,
    )
    {

    }
}


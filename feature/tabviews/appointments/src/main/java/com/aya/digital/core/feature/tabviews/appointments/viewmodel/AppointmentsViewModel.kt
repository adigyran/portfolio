package com.aya.digital.core.feature.tabviews.appointments.viewmodel


import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.orbitmvi.orbit.viewmodel.container

class AppointmentsViewModel(
    private val coordinatorRouter: CoordinatorRouter
) :
    BaseViewModel<AppointmentsState, AppointmentsSideEffects>() {
    override val container = container<AppointmentsState, AppointmentsSideEffects>(
        initialState = AppointmentsState(""),
    )
    {

    }

    fun onAppointmentClicked(appointmentId: Int) {

    }

}


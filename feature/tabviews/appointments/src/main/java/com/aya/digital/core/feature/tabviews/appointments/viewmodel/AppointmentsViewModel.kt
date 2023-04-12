package com.aya.digital.core.feature.tabviews.appointments.viewmodel


import com.aya.digital.core.domain.appointment.base.GetAppointmentsUseCase
import com.aya.digital.core.feature.tabviews.appointments.navigation.AppointmentsNavigationEvents
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.reactive.asFlow
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class AppointmentsViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val getAppointmentsUseCase: GetAppointmentsUseCase
) :
    BaseViewModel<AppointmentsState, AppointmentsSideEffects>() {
    override val container = container<AppointmentsState, AppointmentsSideEffects>(
        initialState = AppointmentsState(),
    )
    {
        loadAppointments()
    }

    private fun loadAppointments() = intent {
        getAppointmentsUseCase().asFlow()
            .collect { resultModel ->
                resultModel.processResult({
                    reduce {
                        state.copy(appointments = it)
                    }
                }, { processError(it) })
            }
    }

    fun onAppointmentClicked(appointmentId: Int) {
        coordinatorRouter.sendEvent(AppointmentsNavigationEvents.OpenVideoCall(appointmentId))
    }

}


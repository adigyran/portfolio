package com.aya.digital.core.feature.tabviews.appointments.viewmodel


import com.aya.digital.core.domain.appointment.base.GetAppointmentsUseCase
import com.aya.digital.core.feature.tabviews.appointments.navigation.AppointmentsNavigationEvents
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.reactive.asFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.isDistantPast
import kotlinx.datetime.minus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
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

    private fun loadAppointments() = intent(registerIdling = false) {
        getAppointmentsUseCase().asFlow()
            .collect { resultModel ->
                resultModel.processResult({
                    reduce {
                        state.copy(appointments = it)
                    }
                }, { processError(it) })
            }
    }

    fun onRefreshAppointments() = intent {
        loadAppointments()
    }
    fun onAppointmentClicked(appointmentId: Int) = intent {
        val appointmentModel = state.appointments?.firstOrNull { it.id == appointmentId }
        appointmentModel?.let {appointment->
            if(!appointmentModel.type.contains("online",true)) return@let
            val currentTimeInstant = Clock.System.now()
            val appointmentTimeInstant = appointment.startDate.toInstant(TimeZone.currentSystemDefault())
            val appointmentTimeStartInstant = appointmentTimeInstant.minus(15, DateTimeUnit.MINUTE)
            if(currentTimeInstant<appointmentTimeStartInstant) return@let
            coordinatorRouter.sendEvent(AppointmentsNavigationEvents.OpenVideoCall(appointmentId))
        }
    }

}


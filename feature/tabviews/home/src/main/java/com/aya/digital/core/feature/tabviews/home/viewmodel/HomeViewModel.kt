package com.aya.digital.core.feature.tabviews.home.viewmodel


import com.aya.digital.core.domain.appointment.base.GetAppointmentsWithParticipantsByStartDayAndDaysUseCase
import com.aya.digital.core.domain.base.models.appointment.AppointmentType
import com.aya.digital.core.domain.doctors.base.GetDoctorsUseCase
import com.aya.digital.core.domain.home.lastupdates.GetLastUpdatesUseCase
import com.aya.digital.core.feature.tabviews.home.ButtonsTags
import com.aya.digital.core.feature.tabviews.home.navigation.HomeNavigationEvents
import com.aya.digital.core.feature.tabviews.home.viewmodel.HomeSideEffects
import com.aya.digital.core.feature.tabviews.home.viewmodel.HomeState
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber
import java.time.Instant
import java.time.ZoneId

const val DAYS_IN_FUTURE = 100L


class HomeViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val getLastUpdatesUseCase: GetLastUpdatesUseCase,
    private val getAppointmentsUseCase: GetAppointmentsWithParticipantsByStartDayAndDaysUseCase,
    private val getDoctorsUseCase: GetDoctorsUseCase,

    ) :
    BaseViewModel<HomeState, HomeSideEffects>() {
    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }

    override val container = container<HomeState, HomeSideEffects>(
        initialState = HomeState(""),
    )
    {
        getLatestUpdates()
        getDoctorsCount()
        getAppointmentsCount()
    }

    private fun getDoctorsCount() = intent {
        getDoctorsUseCase(
            cursor = null,
            cities = null,
            specialities = null,
            insurances = null
        ).asFlow()
            .collect {
                it.processResult({ doctorPaginationModel ->
                    val doctorsCount = doctorPaginationModel.totalItems
                    reduce {
                        state.copy(
                            doctorsCount = doctorsCount
                        )
                    }

                }, { processError(it) })
            }
    }

    private fun getAppointmentsCount() = intent {
        val systemTZ = ZoneId.systemDefault()
        val currentInstant = Instant.now()
        val currentDate = currentInstant.atZone(systemTZ).toLocalDate()
        getAppointmentsUseCase(currentDate, DAYS_IN_FUTURE).asFlow()
            .collect { resultModel ->
                resultModel.processResult({ appointments ->
                    val size = appointments.size
                    val telemedSize =
                        appointments.filter { it.appointmentModel.type is AppointmentType.Online }.size
                    reduce {
                        state.copy(
                            appointmentsCount = size,
                            telemedicineCount = telemedSize
                        )
                    }
                }, { processError(it) })
            }
    }

    private fun getLatestUpdates() = intent {
        getLastUpdatesUseCase().await()
            .processResult({ map ->
                reduce {
                    state.copy(
                        lastUpdates = map
                    )
                }
            }, { processError(it) })
    }

    fun onButtonClick(tag: Int) = intent {
        when (tag) {
            ButtonsTags.FIND_DOCTOR -> openDoctors()
            ButtonsTags.APPOINTMENTS -> openAppointments()
            ButtonsTags.TELEMEDICINE -> openAppointments()
        }
    }

    fun openDoctors() = intent {
        coordinatorRouter.sendEvent(HomeNavigationEvents.FindDoctor)
    }

    fun openAppointments() = intent {
        coordinatorRouter.sendEvent(HomeNavigationEvents.ShowAppointments)
    }

}


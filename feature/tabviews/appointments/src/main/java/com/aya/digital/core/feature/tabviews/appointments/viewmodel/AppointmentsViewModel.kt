package com.aya.digital.core.feature.tabviews.appointments.viewmodel

import com.aya.digital.core.data.base.result.models.appointment.SelectAppointmentResultModel
import com.aya.digital.core.domain.appointment.base.GetAppointmentsWithParticipantsByStartDayAndDaysUseCase
import com.aya.digital.core.domain.appointment.base.GetAppointmentsWithParticipantsUseCase
import com.aya.digital.core.domain.appointment.base.model.AppointmentWithParticipantModel
import com.aya.digital.core.domain.appointment.participants.model.AppointmentDoctorParticipant
import com.aya.digital.core.domain.base.models.appointment.AppointmentModel
import com.aya.digital.core.domain.base.models.appointment.AppointmentType
import com.aya.digital.core.feature.tabviews.appointments.navigation.AppointmentsNavigationEvents
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.AppointmentUiStatus
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.reactive.asFlow
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDateTime
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.Instant
import java.time.ZoneId

const val DAYS_IN_FUTURE = 100L
class AppointmentsViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val rootCoordinatorRouter: CoordinatorRouter,
    private val getAppointmentsUseCase: GetAppointmentsWithParticipantsByStartDayAndDaysUseCase,
) :
    BaseViewModel<AppointmentsState, AppointmentsSideEffects>() {
    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }


    override val container = container<AppointmentsState, AppointmentsSideEffects>(
        initialState = AppointmentsState(),
    )
    {
        loadAppointments()
    }

    private fun loadAppointments() = intent(registerIdling = false) {
        val systemTZ = ZoneId.systemDefault()
        val currentInstant = Instant.now()
        val currentDate = currentInstant.atZone(systemTZ).toLocalDate()
        getAppointmentsUseCase(currentDate, DAYS_IN_FUTURE).asFlow()
            .collect { resultModel ->
                resultModel.processResult({ appointments ->
                    reduce {
                        state.copy(appointments = appointments.map { it.toPatientAppointments() })
                    }
                }, { processError(it) })
            }
    }

    private fun AppointmentWithParticipantModel.toPatientAppointments() =
        AppointmentData(
            id = appointmentModel.id,
            startDate = appointmentModel.startDate.toKotlinLocalDateTime(),
            isTelemed = appointmentModel.type is AppointmentType.Online,
            status = appointmentModel.status,
            doctor = if (appointmentParticipant != null && appointmentParticipant is AppointmentDoctorParticipant) (appointmentParticipant as? AppointmentDoctorParticipant)?.mapToAppointmentDoctor() else null
        )

    private fun AppointmentDoctorParticipant.mapToAppointmentDoctor() = AppointmentDoctor(
        id = id,
        doctorFirstName = firstName,
        doctorLastName = lastName,
        doctorMiddleName = middleName,
        doctorSpecialities = doctorModel.specialities,
        doctorClinics = doctorModel.clinics,
        doctorAvatarImageLink = avatarPhotoLink
    )

    fun onDateSelected(date: LocalDate) = intent {
        listenForAppointmentSelect()
        coordinatorRouter.sendEvent(
            AppointmentsNavigationEvents.OpenAppointmentsForSpecificDate(
                requestCode = RequestCodes.APPOINTMENTS_FOR_DATE_REQUEST_CODE,
                date = date.toJavaLocalDate()
            )
        )
    }

    private fun listenForAppointmentSelect() {
        rootCoordinatorRouter.setResultListener(RequestCodes.APPOINTMENTS_FOR_DATE_REQUEST_CODE) { result ->
            if (result !is SelectAppointmentResultModel) return@setResultListener
            coordinatorRouter.sendEvent(AppointmentsNavigationEvents.OpenAppointment(result.appointmentId))
        }
    }

    fun onRefreshAppointments() = intent {
        loadAppointments()
    }

    fun onAppointmentClicked(appointmentId: Int) = intent {
        val appointmentModel = state.appointments?.firstOrNull { it.id == appointmentId }
        appointmentModel?.let { appointment ->
            coordinatorRouter.sendEvent(AppointmentsNavigationEvents.OpenAppointment(appointmentId))
        }
    }

    fun onHideClicked(status: AppointmentUiStatus) = intent {
        val convertedStatus = status.toAppointmentStatus()
        if (state.expandedStatuses.contains(convertedStatus)) {
            val newExpanded =
                state.expandedStatuses.toMutableSet().apply { remove(convertedStatus) }
            reduce { state.copy(expandedStatuses = newExpanded.toSet()) }
        } else {
            val newExpanded = state.expandedStatuses.toMutableSet().apply { add(convertedStatus) }
            reduce { state.copy(expandedStatuses = newExpanded.toSet()) }
        }
    }

    private fun AppointmentUiStatus.toAppointmentStatus() =
        when (this) {
            AppointmentUiStatus.SCHEDULED -> AppointmentModel.AppointmentStatus.SCHEDULED
            AppointmentUiStatus.CANCELLED -> AppointmentModel.AppointmentStatus.CANCELLED
            AppointmentUiStatus.DONE -> AppointmentModel.AppointmentStatus.DONE
        }

}


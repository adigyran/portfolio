package com.aya.digital.core.feature.tabviews.appointments.viewmodel

import com.aya.digital.core.data.base.result.models.appointment.SelectAppointmentResultModel
import com.aya.digital.core.domain.appointment.base.GetAppointmentsWithParticipantsUseCase
import com.aya.digital.core.domain.appointment.base.model.AppointmentWithParticipantModel
import com.aya.digital.core.domain.base.models.appointment.AppointmentModel
import com.aya.digital.core.domain.base.models.appointment.AppointmentType
import com.aya.digital.core.feature.tabviews.appointments.navigation.AppointmentsNavigationEvents
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.AppointmentUiStatus
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.reactive.asFlow
import kotlinx.datetime.LocalDate
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class AppointmentsViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val rootCoordinatorRouter: CoordinatorRouter,
    private val getAppointmentsUseCase: GetAppointmentsWithParticipantsUseCase,
) :
    BaseViewModel<AppointmentsState, AppointmentsSideEffects>() {
    override fun onBack() {

    }

    override val container = container<AppointmentsState, AppointmentsSideEffects>(
        initialState = AppointmentsState(),
    )
    {
        loadAppointments()
    }

    private fun loadAppointments() = intent(registerIdling = false) {
        getAppointmentsUseCase().asFlow()
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
            startDate = appointmentModel.startDate,
            isTelemed = appointmentModel.type == AppointmentType.Online,
            status = appointmentModel.status,
            doctor = doctorModel?.run {
                AppointmentDoctor(
                    id = id,
                    doctorFirstName = firstName,
                    doctorLastName = lastName,
                    doctorMiddleName = middleName,
                    doctorSpecialities = specialities,
                    doctorClinics = clinics,
                    doctorAvatarImageLink = avatarPhotoLink
                )
            }
        )

    fun onDateSelected(date: LocalDate) = intent {
        listenForAppointmentSelect()
        coordinatorRouter.sendEvent(
            AppointmentsNavigationEvents.OpenAppointmentsForSpecificDate(
                requestCode = RequestCodes.APPOINTMENTS_FOR_DATE_REQUEST_CODE,
                date = date
            )
        )
    }

    private fun listenForAppointmentSelect() {
        rootCoordinatorRouter.setResultListener(RequestCodes.APPOINTMENTS_FOR_DATE_REQUEST_CODE) { result->
            if(result !is SelectAppointmentResultModel) return@setResultListener
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
        if(state.expandedStatuses.contains(convertedStatus))
        {
            val newExpanded = state.expandedStatuses.toMutableSet().apply { remove(convertedStatus)}
            reduce { state.copy(expandedStatuses = newExpanded.toSet()) }
        }
        else
        {
            val newExpanded = state.expandedStatuses.toMutableSet().apply {add(convertedStatus)}
            reduce { state.copy(expandedStatuses = newExpanded.toSet()) }
        }
    }

    private fun AppointmentUiStatus.toAppointmentStatus() =
        when(this)
        {
            AppointmentUiStatus.SCHEDULED -> AppointmentModel.AppointmentStatus.SCHEDULED
            AppointmentUiStatus.CANCELLED -> AppointmentModel.AppointmentStatus.CANCELLED
            AppointmentUiStatus.DONE -> AppointmentModel.AppointmentStatus.DONE
        }

}


package com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.viewmodel

import com.aya.digital.core.data.base.result.models.appointment.SelectAppointmentResultModel
import com.aya.digital.core.domain.appointment.base.GetAppointmentsWithParticipantsByStartDayAndDaysUseCase
import com.aya.digital.core.domain.appointment.base.GetAppointmentsWithParticipantsUseCase
import com.aya.digital.core.domain.appointment.base.model.AppointmentWithParticipantModel
import com.aya.digital.core.domain.appointment.participants.model.AppointmentDoctorParticipant
import com.aya.digital.core.domain.appointment.participants.model.AppointmentPatientParticipant
import com.aya.digital.core.domain.base.models.appointment.AppointmentType
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.navigation.DateAppointmentsDialogNavigationEvents
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.ui.DateAppointmentsDialogView
import kotlinx.coroutines.reactive.asFlow
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class DateAppointmentsDialogViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val param: DateAppointmentsDialogView.Param,
    private val getAppointmentsByIntervalUseCase: GetAppointmentsWithParticipantsUseCase,
    private val getAppointmentsByStartDayAndDaysUseCase: GetAppointmentsWithParticipantsByStartDayAndDaysUseCase
) :
    BaseViewModel<DateAppointmentsDialogState, BaseSideEffect>() {
    override val container = container<DateAppointmentsDialogState, BaseSideEffect>(
        initialState = DateAppointmentsDialogState(
            dateTime = getDateTime(param)
        ),
    )
    {

    }

    init {
        loadAppointments()
    }

    private fun getDateTime(param: DateAppointmentsDialogView.Param) =
        when (val dialogParam = param.dialogParam) {
            is DateAppointmentsDialogView.DialogParam.DateParam -> dialogParam.date.atStartOfDay()
            is DateAppointmentsDialogView.DialogParam.IntervalParam -> dialogParam.startDateTime
        }

    private fun loadAppointments() = intent(registerIdling = false) {
        val getAppointmentsUseCase = when (val dialogParam = param.dialogParam) {
            is DateAppointmentsDialogView.DialogParam.DateParam -> getAppointmentsByStartDayAndDaysUseCase(
                dialogParam.date,
                0
            )

            is DateAppointmentsDialogView.DialogParam.IntervalParam -> getAppointmentsByIntervalUseCase(
                dialogParam.startDateTime,
                dialogParam.endDateTime
            )
        }
        getAppointmentsUseCase.asFlow()
            .collect { resultModel ->
                resultModel.processResult({ appointments ->
                    reduce {
                        state.copy(appointments = appointments.map { it.toParticipantAppointment() })
                    }
                }, { processError(it) })
            }
    }

    private fun AppointmentWithParticipantModel.toParticipantAppointment() =
        AppointmentData(
            id = appointmentModel.id,
            startDate = appointmentModel.startDate,
            isTelemed = appointmentModel.type is AppointmentType.Online,
            status = appointmentModel.status
        ).apply {
            when (appointmentParticipant) {
                is AppointmentDoctorParticipant -> {
                    this.doctor =
                        (appointmentParticipant as AppointmentDoctorParticipant).mapToAppointmentDoctor()
                }

                is AppointmentPatientParticipant -> {
                    this.patient =
                        (appointmentParticipant as AppointmentPatientParticipant).mapToAppointmentPatient()
                }

                null -> {

                }
            }
        }

    private fun AppointmentDoctorParticipant.mapToAppointmentDoctor() = AppointmentDoctor(
        id = id,
        doctorFirstName = firstName,
        doctorLastName = lastName,
        doctorMiddleName = middleName,
        doctorSpecialities = doctorModel.specialities,
        doctorClinics = doctorModel.clinics,
        doctorAvatarImageLink = avatarPhotoLink
    )

    private fun AppointmentPatientParticipant.mapToAppointmentPatient() = AppointmentPatient(
        id = id,
        firstName = firstName,
        lastName = lastName,
        middleName = middleName,
        patientAvatarImageLink = avatarPhotoLink,
        birthDay = null
    )

    fun onAppointmentClicked(appointmentId: Int) = intent {
        val appointmentModel = state.appointments?.firstOrNull { it.id == appointmentId }
        appointmentModel?.let { appointment ->
            coordinatorRouter.sendEvent(
                DateAppointmentsDialogNavigationEvents.FinishWithResult(
                    param.requestCode,
                    SelectAppointmentResultModel(appointmentId)
                )
            )
        }
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) {
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }
}


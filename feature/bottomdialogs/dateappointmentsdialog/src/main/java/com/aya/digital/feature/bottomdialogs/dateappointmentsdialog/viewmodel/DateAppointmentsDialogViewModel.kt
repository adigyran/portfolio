package com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.viewmodel

import com.aya.digital.core.data.base.result.models.appointment.SelectAppointmentResultModel
import com.aya.digital.core.domain.appointment.base.GetAppointmentsWithParticipantsUseCase
import com.aya.digital.core.domain.appointment.base.model.AppointmentWithParticipantModel
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
    private val getAppointmentsUseCase: GetAppointmentsWithParticipantsUseCase
) :
    BaseViewModel<DateAppointmentsDialogState, BaseSideEffect>() {
    override val container = container<DateAppointmentsDialogState, BaseSideEffect>(
        initialState = DateAppointmentsDialogState(
            date = param.date
        ),
    )
    {

    }

    init {
        loadAppointments()
    }

    private fun loadAppointments() = intent(registerIdling = false) {
        getAppointmentsUseCase(param.date).asFlow()
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
            isTelemed = appointmentModel.type is AppointmentType.Online,
            status =  appointmentModel.status,
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

    fun onAppointmentClicked(appointmentId: Int) = intent {
        val appointmentModel = state.appointments?.firstOrNull { it.id == appointmentId }
        appointmentModel?.let { appointment ->
            coordinatorRouter.sendEvent(DateAppointmentsDialogNavigationEvents.FinishWithResult(param.requestCode,
                SelectAppointmentResultModel(appointmentId)
            ))
        }
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) {
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }
}


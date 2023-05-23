package com.aya.digital.core.feature.appointments.appointmentcard.viewmodel

import com.aya.digital.core.domain.appointment.base.CancelAppointmentByIdUseCase
import com.aya.digital.core.domain.appointment.base.GetAppointmentByIdWithParticipantUseCase
import com.aya.digital.core.domain.base.models.appointment.AppointmentModel
import com.aya.digital.core.domain.base.models.appointment.AppointmentType
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.domain.base.models.patients.PatientModel
import com.aya.digital.core.feature.appointments.appointmentcard.navigation.AppointmentCardNavigationEvents
import com.aya.digital.core.feature.appointments.appointmentcard.ui.AppointmentCardView
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toInstant
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

internal class AppointmentCardViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val rootCoordinatorRouter: CoordinatorRouter,
    private val param: AppointmentCardView.Param,
    private val getAppointmentByIdWithParticipantUseCase: GetAppointmentByIdWithParticipantUseCase,
    private val cancelAppointmentByIdUseCase: CancelAppointmentByIdUseCase,
) :
    BaseViewModel<AppointmentCardState, AppointmentCardSideEffects>() {
    override val container = container<AppointmentCardState, AppointmentCardSideEffects>(
        initialState = AppointmentCardState(),
    )
    {
        loadAppointment()
    }

    private fun loadAppointment() = intent {
        getAppointmentByIdWithParticipantUseCase(param.appointmentId)
            .await()
            .processResult({ appointmentWithParticipantModel ->
                showAppointment(appointmentWithParticipantModel.appointmentModel)
                appointmentWithParticipantModel.doctorModel?.let { showDoctor(it) }
                appointmentWithParticipantModel.patientModel?.let { showPatient(it) }
            }, { processError(it) })
    }

    fun onCancelAppointment() = intent {
        cancelAppointment()
    }


    fun onRescheduleAppointment() = intent {
        rescheduleAppointment()
    }

    private fun cancelAppointment() = intent {
        cancelAppointmentByIdUseCase(param.appointmentId)
            .await()
            .processResult({ onBack() }, {
                processError(it)
            })
    }

    private fun rescheduleAppointment() = intent {

    }

    fun onCommentReadMoreClicked() = intent {
        val readMoreOld = state.commentReadMore
        reduce { state.copy(commentReadMore = !readMoreOld) }
    }

    fun onTelemedClicked() = intent {
        openVideoCall()
    }

    private fun showAppointment(appointmentModel: AppointmentModel) = intent {

        reduce {
            state.copy(
                appointmentDate = appointmentModel.startDate,
                appointmentComment = appointmentModel.comment,
                isTelemed = appointmentModel.type == AppointmentType.Online,
            )
        }
    }

    private fun showDoctor(doctorModel: DoctorModel) = intent {
        val doctorData = DoctorData(
            doctorFirstName = doctorModel.firstName,
            doctorLastName = doctorModel.lastName,
            doctorMiddleName = doctorModel.middleName,
            doctorAddress = doctorModel.address,
            doctorClinics = doctorModel.clinics,
            doctorSpecialities = doctorModel.specialities,
            doctorInsurances = doctorModel.insurances
        )
        reduce {
            state.copy(
                participantAvatar = doctorModel.avatarPhotoLink,
                doctorData = doctorData
            )
        }
    }

    private fun showPatient(patientModel: PatientModel) = intent {
        val patientData = PatientData(
            patientFirstName = patientModel.firstName,
            patientLastName = patientModel.lastName,
            patientBirthDate = patientModel.birthDate,
            patientInsurances = patientModel.insurances
        )
        reduce {
            state.copy(
                participantAvatar = patientModel.avatarPhotoLink,
                patientData = patientData
            )
        }
    }

    private fun openVideoCall() = intent {
        state.appointmentDate?.let { startDate ->
            val currentTimeInstant = Clock.System.now()
            val appointmentTimeInstant = startDate.toInstant(TimeZone.currentSystemDefault())
            val appointmentTimeStartInstant = appointmentTimeInstant.minus(15, DateTimeUnit.MINUTE)
            if (currentTimeInstant < appointmentTimeStartInstant) return@intent
            coordinatorRouter.sendEvent(AppointmentCardNavigationEvents.OpenVideoCall(param.appointmentId))
        }
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(AppointmentCardSideEffects.Error(errorSideEffect))
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }

}


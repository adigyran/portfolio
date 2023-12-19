package com.aya.digital.core.feature.videocall.videocallscreen.viewmodel

import androidx.lifecycle.viewModelScope
import com.aya.digital.core.domain.appointment.base.GetAppointmentByIdWithParticipantUseCase
import com.aya.digital.core.domain.prescriptions.base.SubscribeToPrescriptionsUseCase
import com.aya.digital.core.domain.appointment.participants.model.AppointmentDoctorParticipant
import com.aya.digital.core.domain.appointment.participants.model.AppointmentPatientParticipant
import com.aya.digital.core.domain.appointment.telehealth.GetTeleHealthRoomTokenUseCase
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.domain.base.models.patients.PatientModel
import com.aya.digital.core.feature.videocall.videocallscreen.navigation.VideoCallScreenNavigationEvents
import com.aya.digital.core.feature.videocall.videocallscreen.ui.VideoCallScreenView
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.AppFlavour
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.rx3.await
import kotlinx.coroutines.time.delay
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber
import java.time.Duration
import java.time.Instant

class VideoCallScreenViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val param: VideoCallScreenView.Param,
    private val getTeleHealthRoomTokenUseCase: GetTeleHealthRoomTokenUseCase,
    private val getAppointmentByIdWithParticipantUseCase: GetAppointmentByIdWithParticipantUseCase,
    private val subscribeToPrescriptionsUseCase: SubscribeToPrescriptionsUseCase,
    private val appFlavour: AppFlavour
) :
    BaseViewModel<VideoCallScreenState, VideoCallScreenSideEffects>() {
    override val container = container<VideoCallScreenState, VideoCallScreenSideEffects>(
        initialState = VideoCallScreenState(),
    )
    {
        initialiseState()
        loadAppointment()
    }

    private lateinit var timerJob:Job

    private fun initialiseState() = intent {
        reduce {
            state.copy(
                localAudioEnabled = false,
                localVideoEnabled = false,
                callState = CallState.Initial,
                participantState = ParticipantState.ParticipantNotConnected
            )
        }
    }

    private fun loadAppointment() = intent {
        getAppointmentByIdWithParticipantUseCase(param.roomId)
            .await()
            .processResult({ appointmentWithParticipantModel ->
                when (val appointmentParticipant = appointmentWithParticipantModel.appointmentParticipant) {
                    is AppointmentDoctorParticipant -> showDoctor(appointmentParticipant.doctorModel)
                    is AppointmentPatientParticipant -> showPatient(appointmentParticipant.patientModel)
                    null -> {

                    }
                }
            }, { processError(it) })
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
                //   participantAvatar = doctorModel.avatarPhotoLink,
                doctorData = doctorData,
                participantFirstName = doctorData.doctorFirstName,
                participantLastName = doctorData.doctorLastName
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
                // participantAvatar = patientModel.avatarPhotoLink,
                patientData = patientData,
                participantFirstName = patientData.patientFirstName,
                participantLastName = patientData.patientLastName
            )
        }
    }

    fun updateCallState(callState: CallState) = intent {
        if(callState!=state.callState){ reduce { state.copy(callState = callState) }
            processCallState()
        }
    }

    fun updateParticipantState(participantState:ParticipantState) = intent {
        if(participantState!=state.participantState) {
            reduce { state.copy(participantState = participantState) }
            processParticipantState()
        }
    }


    private fun processCallState() = intent {
        if(state.callState.isConnected) startTimer()
        else if(state.callState.isDisconnected) stopTimer()
    }

    private fun processParticipantState() = intent {
        when(state.participantState)
        {
            ParticipantState.ParticipantDisconnected -> {
                onParticipantDisconnected()
            }
            else -> {}
        }
    }


    private fun startTimer() = intent {
        val startingInstant = if(state.lastInstant<Instant.now()) state.lastInstant else Instant.now()
        reduce { state.copy(firstInstant = startingInstant) }
        var duration=Duration.ofSeconds(0)
        timerJob = viewModelScope.launch {
            tickerFlow(Duration.ofSeconds(1))
                .cancellable()
                .map { Instant.now() }
             /*   .distinctUntilChanged { old, new ->
                    old == new
                }*/
                .onEach {
                    duration= Duration.between(startingInstant,it)
                }
                .buffer()
                .collect{
                    reduce { state.copy(lastInstant = it, videoCallDuration = duration) }
                }
        }
        timerJob.start()

    }

    private fun stopTimer() = intent {
        timerJob.cancel()
    }

    private fun onParticipantDisconnected() = intent {
        postSideEffect(VideoCallScreenSideEffects.ShowCallEndedDialog)
    }

    fun toggleConnectionClicked() = intent {
        when (state.callState.inActiveCall) {
            true -> showDisconnectDialog()
            false -> connect()
        }
    }

    fun togglePipMode(isPip: Boolean) = intent {
        reduce { state.copy(pipMode = isPip) }
    }


    fun resumeOngoingConnection() = intent {
        if (state.callState.inActiveCall) connect()
    }

    private fun connect() = intent {
        getRoomToken()
    }

    private fun showDisconnectDialog() = intent {
        postSideEffect(VideoCallScreenSideEffects.ShowDisconnectDialog)
    }

    fun onDisconnectConfirmed() = intent {
        disconnect()
        exit()
    }

    private fun disconnect() = intent {
        reduce { state.copy(callState = CallState.Disconnected) }
    }

    private fun exit() {
        coordinatorRouter.sendEvent(VideoCallScreenNavigationEvents.Back)

    }

    fun toggleLocalVideoClicked() = intent {
        reduce { state.copy(localVideoEnabled = !state.localVideoEnabled) }
    }

    fun toggleLocalAudioClicked() = intent {
        reduce { state.copy(localAudioEnabled = !state.localAudioEnabled) }
    }


    private fun getRoomToken() = intent {
        getTeleHealthRoomTokenUseCase(param.roomId)
            .await()
            .processResult({ token ->
                reduce { state.copy(roomToken = token) }
                postSideEffect(VideoCallScreenSideEffects.ConnectToRoom("${param.roomId}", token))
            }, { processError(it) })
    }

    override fun onCleared() {
        disconnect()
        super.onCleared()
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(VideoCallScreenSideEffects.Error(errorSideEffect))
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }

    private fun tickerFlow(period: Duration=Duration.ofSeconds(1)) = flow {
        while (true) {
            emit(Unit)
            delay(period)
        }
    }
}


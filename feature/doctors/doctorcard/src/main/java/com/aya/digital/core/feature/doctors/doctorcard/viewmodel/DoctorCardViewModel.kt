package com.aya.digital.core.feature.doctors.doctorcard.viewmodel

import com.aya.digital.core.domain.doctors.base.GetDoctorByIdUseCase
import com.aya.digital.core.domain.schedule.base.GetLatestScheduleByDoctorIdUseCase
import com.aya.digital.core.domain.schedule.base.model.ScheduleSlotModel
import com.aya.digital.core.feature.doctors.doctorcard.DoctorCardMode
import com.aya.digital.core.feature.doctors.doctorcard.navigation.DoctorCardNavigationEvents
import com.aya.digital.core.feature.doctors.doctorcard.ui.DoctorCardView
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.rx3.await
import kotlinx.datetime.LocalDate
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class DoctorCardViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val param: DoctorCardView.Param,
    private val getDoctorByIdUseCase: GetDoctorByIdUseCase,
    private val getLatestScheduleByDoctorIdUseCase: GetLatestScheduleByDoctorIdUseCase

) :
    BaseViewModel<DoctorCardState, BaseSideEffect>() {
    override val container = container<DoctorCardState, BaseSideEffect>(
        initialState = DoctorCardState(),
    )
    {
        loadDoctorSchedule(param.doctorId)
        loadDoctor(param.doctorId)
    }

    private fun loadDoctor(doctorId: Int) = intent {
        getDoctorByIdUseCase(doctorId).await()
            .processResult({
                reduce {
                    state.copy(
                        doctorAvatar = it.avatarPhotoLink,
                        doctorFirstName = it.firstName,
                        doctorLastName = it.lastName,
                        doctorMiddleName = it.middleName,
                        doctorBio = it.bio,
                        doctorAddress = it.address,
                        doctorClinics = it.clinics,
                        doctorLocation = it.location,
                        doctorSpecialities = it.specialities,
                        doctorInsurances = it.insurances
                    )
                }
            }, { processError(it) })
    }

    private fun loadDoctorSchedule(doctorId: Int) = intent(registerIdling = false) {
        getLatestScheduleByDoctorIdUseCase(doctorId, days = 3).asFlow()
            .collect {
                it.processResult({ scheduleSlotModels ->
                    updateSlots(slots = scheduleSlotModels)
                }, { processError(it) })
            }
    }

    private fun updateSlots(slots: List<ScheduleSlotModel>) = intent {
        reduce {
            state.copy(
                doctorSlots = slots
            )
        }
    }

    fun onSlotClicked(slotId: Int, date:LocalDate?) = intent {
        if(date!=null)
        {
            listenForAppointmentCreation()
            coordinatorRouter.sendEvent(DoctorCardNavigationEvents.CreateAppointment(
                requestCode =  RequestCodes.CREATE_APPOINTMENT_REQUEST_COOE,
                doctorId = param.doctorId,
                slotDateTime = null,
                date = date
            ))
        }
        else
        {
            state.doctorSlots?.firstOrNull { it.id == slotId }?.let {slot->
                listenForAppointmentCreation()
                coordinatorRouter.sendEvent(DoctorCardNavigationEvents.CreateAppointment(
                    requestCode =  RequestCodes.CREATE_APPOINTMENT_REQUEST_COOE,
                    doctorId = param.doctorId,
                    slotDateTime = slot.startDate,
                    date = slot.startDate.date
                ))
            }
        }
    }

    private fun listenForAppointmentCreation() {
        coordinatorRouter.setResultListener(RequestCodes.CREATE_APPOINTMENT_REQUEST_COOE) {
        }
    }

    fun onBioReadMoreClicked() = intent {
        val readMoreOld = state.bioReadMore
        reduce { state.copy(bioReadMore = !readMoreOld) }
    }
    fun onBookClicked() = intent {
        if (state.doctorCardMode != DoctorCardMode.ShowingSlots) reduce {
            state.copy(doctorCardMode = DoctorCardMode.ShowingSlots)
        }
    }
    fun onDetailsClicked() = intent {
        if (state.doctorCardMode != DoctorCardMode.ShowingDetailsInfo) reduce {
            state.copy(doctorCardMode = DoctorCardMode.ShowingDetailsInfo)
        }
    }

}


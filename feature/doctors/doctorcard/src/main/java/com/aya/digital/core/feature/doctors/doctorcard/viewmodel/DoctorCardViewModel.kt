package com.aya.digital.core.feature.doctors.doctorcard.viewmodel

import com.aya.digital.core.data.base.result.models.appointment.CreateAppointmentResultModel
import com.aya.digital.core.domain.doctors.base.GetDoctorByIdUseCase
import com.aya.digital.core.domain.doctors.favourites.AddDoctorToFavoritesUseCase
import com.aya.digital.core.domain.doctors.favourites.CheckDoctorIsInFavoritesUseCase
import com.aya.digital.core.domain.doctors.favourites.RemoveDoctorFromFavoritesUseCase
import com.aya.digital.core.domain.schedule.base.GetLatestScheduleByDoctorIdUseCase
import com.aya.digital.core.domain.schedule.base.model.ScheduleSlotModel
import com.aya.digital.core.domain.schedule.patient.selectable.GetSelectableScheduleByDoctorIdUseCase
import com.aya.digital.core.feature.doctors.doctorcard.DoctorCardMode
import com.aya.digital.core.feature.doctors.doctorcard.navigation.DoctorCardNavigationEvents
import com.aya.digital.core.feature.doctors.doctorcard.ui.DoctorCardView
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.rx3.await
import kotlinx.datetime.LocalDate
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class DoctorCardViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val rootCoordinatorRouter: CoordinatorRouter,
    private val param: DoctorCardView.Param,
    private val getDoctorByIdUseCase: GetDoctorByIdUseCase,
    private val getLatestScheduleByDoctorIdUseCase: GetLatestScheduleByDoctorIdUseCase,
    private val getSelectableScheduleByDoctorIdUseCase: GetSelectableScheduleByDoctorIdUseCase,
    private val checkDoctorIsInFavoritesUseCase: CheckDoctorIsInFavoritesUseCase,
    private val addDoctorToFavoritesUseCase: AddDoctorToFavoritesUseCase,
    private val removeDoctorFromFavoritesUseCase: RemoveDoctorFromFavoritesUseCase
) :
    BaseViewModel<DoctorCardState, DoctorCardSideEffects>() {
    override val container = container<DoctorCardState, DoctorCardSideEffects>(
        initialState = DoctorCardState(),
    )
    {
        loadDoctorSchedule(param.doctorId)
        loadDoctor(param.doctorId)
        checkIsFavorite()
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

    fun onSlotClicked(slotId: Int, date: LocalDate?) = intent {
        if (date != null) {
            listenForAppointmentCreation()
            coordinatorRouter.sendEvent(
                DoctorCardNavigationEvents.CreateAppointment(
                    requestCode = RequestCodes.CREATE_APPOINTMENT_REQUEST_CODE,
                    doctorId = param.doctorId,
                    slotDateTime = null,
                    date = date
                )
            )
        } else {
            state.doctorSlots?.firstOrNull { it.id == slotId }?.let { slot ->
                listenForAppointmentCreation()
                coordinatorRouter.sendEvent(
                    DoctorCardNavigationEvents.CreateAppointment(
                        requestCode = RequestCodes.CREATE_APPOINTMENT_REQUEST_CODE,
                        doctorId = param.doctorId,
                        slotDateTime = slot.startDate,
                        date = slot.startDate.date
                    )
                )
            }
        }
    }

    fun onChooseDateClicked() = intent {
        chooseDate()
    }

    private fun chooseDate() = intent(registerIdling = false) {
        getSelectableScheduleByDoctorIdUseCase(param.doctorId, days = 50).asFlow()
            .collect { scheduleResult ->
                scheduleResult.processResult(
                    { scheduleModels -> showDateSelector(scheduleModels.map { it.date }) },
                    { processError(it) })
            }
    }

    private fun showDateSelector(enabledDates: List<LocalDate>) = intent {
        postSideEffect(DoctorCardSideEffects.ShowCustomDateDialog(enabledDates))
    }

    fun onDateSelected(date: LocalDate) = intent {
        coordinatorRouter.sendEvent(
            DoctorCardNavigationEvents.CreateAppointment(
                requestCode = RequestCodes.CREATE_APPOINTMENT_REQUEST_CODE,
                doctorId = param.doctorId,
                slotDateTime = null,
                date = date
            )
        )
    }

    private fun listenForAppointmentCreation() {
        rootCoordinatorRouter.setResultListener(RequestCodes.CREATE_APPOINTMENT_REQUEST_CODE) { result ->
            if (result !is CreateAppointmentResultModel) return@setResultListener
            loadDoctorSchedule(param.doctorId)
            coordinatorRouter.sendEvent(
                DoctorCardNavigationEvents.OpenSuccessAppointmentCreation(
                    requestCode = RequestCodes.SUCCESS_APPOINTMENT_REQUEST_CODE,
                    appointmentId = result.appointmentId
                )
            )
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

    fun onFavoriteClicked() = intent {
        toggleFavorite()
    }

    private fun toggleFavorite() = intent {
        if(state.isFavorite)
        {
            removeDoctorFromFavoritesUseCase(param.doctorId).await()
                .processResult({checkIsFavorite()},{processError(it)})
        }
        else
        {
            addDoctorToFavoritesUseCase(param.doctorId).await()
                .processResult({checkIsFavorite()},{processError(it)})
        }
    }

    private fun checkIsFavorite() = intent {
        checkDoctorIsInFavoritesUseCase(param.doctorId).await()
            .processResult({ result ->
                reduce {
                    state.copy(
                        isFavorite = result
                    )
                }
            }, { processError(it) })
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(DoctorCardSideEffects.Error(errorSideEffect))
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }

}


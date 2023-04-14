package com.aya.digital.core.feature.doctors.doctorcard.viewmodel

import com.aya.digital.core.domain.doctors.base.GetDoctorByIdUseCase
import com.aya.digital.core.domain.schedule.base.GetLatestScheduleByDoctorIdUseCase
import com.aya.digital.core.feature.doctors.doctorcard.DoctorCardMode
import com.aya.digital.core.feature.doctors.doctorcard.ui.DoctorCardView
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
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

    fun onSlotClicked(slotId:Int) = intent {

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


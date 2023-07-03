package com.aya.digital.core.feature.doctors.doctorsfiltersview.viewmodel

import com.aya.digital.core.data.base.result.models.appointment.CreateAppointmentResultModel
import com.aya.digital.core.domain.doctors.base.GetDoctorByIdUseCase
import com.aya.digital.core.domain.doctors.favourites.AddDoctorToFavoritesUseCase
import com.aya.digital.core.domain.doctors.favourites.CheckDoctorIsInFavoritesUseCase
import com.aya.digital.core.domain.doctors.favourites.RemoveDoctorFromFavoritesUseCase
import com.aya.digital.core.domain.schedule.base.GetLatestScheduleByDoctorIdUseCase
import com.aya.digital.core.domain.schedule.base.model.ScheduleSlotModel
import com.aya.digital.core.domain.schedule.selectable.GetSelectableScheduleByDoctorIdUseCase
import com.aya.digital.core.feature.doctors.doctorsfiltersview.navigation.DoctorsFiltersViewNavigationEvents
import com.aya.digital.core.feature.doctors.doctorsfiltersview.ui.DoctorsFiltersView
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
    private val rootCoordinatorRouter: CoordinatorRouter
) :
    BaseViewModel<DoctorCardState, DoctorCardSideEffects>() {
    override val container = container<DoctorCardState, DoctorCardSideEffects>(
        initialState = DoctorCardState(),
    )
    {

    }


    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(DoctorCardSideEffects.Error(errorSideEffect))
    }

    override fun onBack() {
    }

}


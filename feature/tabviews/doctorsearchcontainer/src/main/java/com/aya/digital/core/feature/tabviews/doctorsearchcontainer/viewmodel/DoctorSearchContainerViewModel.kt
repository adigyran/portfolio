package com.aya.digital.core.feature.tabviews.doctorsearchcontainer.viewmodel


import com.aya.digital.core.data.base.dataprocessing.dataloading.DataLoadingOperationWithPagination
import com.aya.digital.core.data.base.dataprocessing.dataloading.enums.OperationState
import com.aya.digital.core.data.base.result.models.dictionaries.MultiSelectResultModel
import com.aya.digital.core.domain.doctors.base.GetDoctorsUseCase
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.domain.doctors.favourites.AddDoctorToFavoritesUseCase
import com.aya.digital.core.domain.doctors.favourites.GetFavoriteDoctorsUseCase
import com.aya.digital.core.domain.doctors.favourites.RemoveDoctorFromFavoritesUseCase
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.CurrentMode
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.navigation.DoctorSearchContainerNavigationEvents
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.viewmodel.model.SelectedFilterModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class DoctorSearchContainerViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val rootCoordinatorRouter: CoordinatorRouter) :
    BaseViewModel<DoctorSearchContainerState, DoctorSearchContainerSideEffects>() {

    override val container = container<DoctorSearchContainerState, DoctorSearchContainerSideEffects>(
        initialState = DoctorSearchContainerState(),
    )
    {

    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }


    fun toggleMode() = intent {
        val currentMode = when (state.currentMode) {
            CurrentMode.List -> {
                CurrentMode.Map
            }

            CurrentMode.Map -> {
                CurrentMode.List
            }
        }
        reduce { state.copy(currentMode = currentMode) }
    }

}


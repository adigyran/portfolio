package com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.viewmodel


import com.aya.digital.core.domain.doctors.favourites.AddDoctorToFavoritesUseCase
import com.aya.digital.core.domain.doctors.favourites.GetFavoriteDoctorsUseCase
import com.aya.digital.core.domain.doctors.favourites.RemoveDoctorFromFavoritesUseCase
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.FieldsTags
import com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.navigation.DoctorsClusterListDialogNavigationEvents
import com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.ui.DoctorsClusterListDialogView
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.rx3.await
import kotlinx.datetime.LocalDate
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class DoctorsClusterListDialogViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val param: DoctorsClusterListDialogView.Param,
    private val getFavoriteDoctors: GetFavoriteDoctorsUseCase,
    private val addDoctorToFavoritesUseCase: AddDoctorToFavoritesUseCase,
    private val removeDoctorFromFavoritesUseCase: RemoveDoctorFromFavoritesUseCase
) :
    BaseViewModel<DoctorsClusterListDialogState, BaseSideEffect>() {
    override val container = container<DoctorsClusterListDialogState, BaseSideEffect>(
        initialState = DoctorsClusterListDialogState(doctors = param.doctors),
    )
    {

    }

    init {
        loadFavoriteDoctors()
    }


    fun onDoctorClicked(doctorId: Int) = intent {
        coordinatorRouter.sendEvent(DoctorsClusterListDialogNavigationEvents.OpenDoctorCard(doctorId = doctorId))
    }

    fun close() = intent {
        coordinatorRouter.sendEvent(DoctorsClusterListDialogNavigationEvents.Exit)
    }


    fun onDoctorFavouriteClicked(doctorId: Int) = intent {
        toggleDoctorFavourite(doctorId)
    }

    private fun toggleDoctorFavourite(doctorId: Int) = intent {
        if (state.favoriteDoctors?.firstOrNull { it == doctorId } != null) {
            removeDoctorFromFavoritesUseCase(doctorId).await()
                .processResult({
                    loadFavoriteDoctors()
                }, { processError(it) })
        } else {
            addDoctorToFavoritesUseCase(doctorId).await()
                .processResult({ loadFavoriteDoctors() }, { processError(it) })
        }
    }

    private fun loadFavoriteDoctors() = intent(registerIdling = false) {
        getFavoriteDoctors().asFlow()
            .collect { resultModel ->
                resultModel.processResult({ doctors ->
                    reduce {
                        state.copy(
                            favoriteDoctors = doctors,
                        )
                    }
                }, { processError(it) })
            }
    }


    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) {
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }
}


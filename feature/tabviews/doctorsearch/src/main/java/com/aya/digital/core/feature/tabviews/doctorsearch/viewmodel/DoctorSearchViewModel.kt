package com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel


import com.aya.digital.core.domain.doctors.base.GetDoctorsUseCase
import com.aya.digital.core.feature.tabviews.doctorsearch.navigation.DoctorSearchNavigationEvents
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.reactive.asFlow
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class DoctorSearchViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val getDoctorsUseCase: GetDoctorsUseCase
) :
    BaseViewModel<DoctorSearchState, DoctorSearchSideEffects>() {
    override val container = container<DoctorSearchState, DoctorSearchSideEffects>(
        initialState = DoctorSearchState(),
    )
    {
        loadDoctors()
    }

    private fun loadDoctors() = intent(registerIdling = false) {
        getDoctorsUseCase().asFlow()
            .collect { resultModel ->
                resultModel.processResult({ doctors ->
                    reduce { state.copy(doctors = doctors) }
                }, { processError(it) })
            }
    }

    fun onDoctorClicked(doctorId:Int) = intent {
        coordinatorRouter.sendEvent(DoctorSearchNavigationEvents.OpenDoctorCard(doctorId = doctorId))

    }

    fun onRefreshDoctors() = intent {
        loadDoctors()
    }

    fun findDoctorClicked() = intent {
        coordinatorRouter.sendEvent(DoctorSearchNavigationEvents.OpenDoctorCard(doctorId = 1284))
    }

}


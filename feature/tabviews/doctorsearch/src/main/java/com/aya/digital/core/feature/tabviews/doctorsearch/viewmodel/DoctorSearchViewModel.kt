package com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel


import com.aya.digital.core.feature.tabviews.doctorsearch.navigation.DoctorSearchNavigationEvents
import com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel.DoctorSearchSideEffects
import com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel.DoctorSearchState
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container

class DoctorSearchViewModel(
    private val coordinatorRouter: CoordinatorRouter
) :
    BaseViewModel<DoctorSearchState, DoctorSearchSideEffects>() {
    override val container = container<DoctorSearchState, DoctorSearchSideEffects>(
        initialState = DoctorSearchState(""),
    )
    {

    }

    fun findDoctorClicked() = intent {
        coordinatorRouter.sendEvent(DoctorSearchNavigationEvents.OpenDoctorCard(doctorId = 1284))
    }

}


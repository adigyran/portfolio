package com.aya.digital.core.feature.doctors.doctorsfiltersview.viewmodel

import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class DoctorsFiltersViewViewModel(
    private val rootCoordinatorRouter: CoordinatorRouter
) :
    BaseViewModel<DoctorsFiltersViewState, DoctorsFiltersViewSideEffects>() {
    override val container = container<DoctorsFiltersViewState, DoctorsFiltersViewSideEffects>(
        initialState = DoctorsFiltersViewState(),
    )
    {

    }


    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(DoctorsFiltersViewSideEffects.Error(errorSideEffect))
    }

    override fun onBack() {
    }

}


package com.aya.digital.core.feature.doctors.doctorcard.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class DoctorCardViewModel(
    private val coordinatorRouter: CoordinatorRouter
) :
    BaseViewModel<DoctorCardState, BaseSideEffect>() {
    override val container = container<DoctorCardState, BaseSideEffect>(
        initialState = DoctorCardState(),
    )
    {
    }



}


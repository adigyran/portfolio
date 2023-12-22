package com.aya.digital.core.feature.prescriptions.list.viewmodel

import com.aya.digital.core.domain.profile.insurance.DeleteInsuranceUseCase
import com.aya.digital.core.domain.profile.insurance.GetInsurancesUseCase
import com.aya.digital.core.feature.prescriptions.list.navigation.ProfilePrescriptionsListNavigationEvents
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.rx3.asFlow
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProfilePrescriptionsListViewModel(
    private val coordinatorRouter: CoordinatorRouter
) :
    BaseViewModel<ProfilePrescriptionsListState, ProfilePrescriptionsListSideEffects>() {
    override val container = container<ProfilePrescriptionsListState, ProfilePrescriptionsListSideEffects>(
        initialState = ProfilePrescriptionsListState(),
    )
    {
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(ProfilePrescriptionsListSideEffects.Error(errorSideEffect))
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }
}


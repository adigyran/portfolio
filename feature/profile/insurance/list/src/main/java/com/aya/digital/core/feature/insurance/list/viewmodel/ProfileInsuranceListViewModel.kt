package com.aya.digital.core.feature.insurance.list.viewmodel

import com.aya.digital.core.feature.insurance.list.navigation.ProfileInsuranceListNavigationEvents
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class ProfileInsuranceListViewModel(
    private val coordinatorRouter: CoordinatorRouter
) :
    BaseViewModel<ProfileInsuranceListState, BaseSideEffect>() {
    override val container = container<ProfileInsuranceListState, BaseSideEffect>(
        initialState = ProfileInsuranceListState(),
    )
    {

    }

    fun insuranceItemClicked(id: Int) = intent {
        coordinatorRouter.sendEvent(ProfileInsuranceListNavigationEvents.EditInsurance(id))
    }

    fun insuranceItemMoreClicked(id: Int) = intent {

    }

    fun addInsuranceClicked() = intent {
        coordinatorRouter.sendEvent(ProfileInsuranceListNavigationEvents.AddInsurance)
    }

}


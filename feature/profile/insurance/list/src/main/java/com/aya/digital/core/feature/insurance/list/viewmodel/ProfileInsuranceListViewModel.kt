package com.aya.digital.core.feature.insurance.list.viewmodel

import com.aya.digital.core.domain.profile.insurance.DeleteInsuranceUseCase
import com.aya.digital.core.domain.profile.insurance.GetInsurancesUseCase
import com.aya.digital.core.feature.insurance.list.navigation.ProfileInsuranceListNavigationEvents
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.rx3.asFlow
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProfileInsuranceListViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val getInsurancesUseCase: GetInsurancesUseCase,
    private val deleteInsuranceUseCase: DeleteInsuranceUseCase,
) :
    BaseViewModel<ProfileInsuranceListState, ProfileInsuranceListSideEffects>() {
    override val container = container<ProfileInsuranceListState, ProfileInsuranceListSideEffects>(
        initialState = ProfileInsuranceListState(),
    )
    {
        loadInsurancesList()
    }

    private fun loadInsurancesList() = intent(registerIdling = false) {
        getInsurancesUseCase().asFlow().collect { result ->
            result.processResult({ models ->
                reduce { state.copy(insuranceModels = models) }
            }, { processError(it) })
        }
    }

    fun insuranceItemClicked(id: Int) = intent {
        listenForInsuranceEditEvent()
        coordinatorRouter.sendEvent(
            ProfileInsuranceListNavigationEvents.EditInsurance(
                RequestCodes.EDIT_INSURANCE_REQUEST_CODE,
                id
            )
        )
    }

    fun addInsuranceClicked() = intent {
        listenForInsuranceAddEvent()
        coordinatorRouter.sendEvent(ProfileInsuranceListNavigationEvents.AddInsurance(RequestCodes.ADD_INSURANCE_REQUEST_CODE))
    }

    fun insuranceItemMoreClicked(id: Int) = intent {
        postSideEffect(ProfileInsuranceListSideEffects.ShowInsuranceActionsDialog(id))
    }

    fun deleteInsurance(id: Int) = intent {
        deleteInsuranceUseCase(id).await()
            .processResult({
                loadInsurancesList()
            }, { processError(it) })
    }

    private fun listenForInsuranceEditEvent() = intent {
        coordinatorRouter.setResultListener(RequestCodes.EDIT_INSURANCE_REQUEST_CODE) {
            loadInsurancesList()
        }
    }

    private fun listenForInsuranceAddEvent() = intent {
        coordinatorRouter.setResultListener(RequestCodes.ADD_INSURANCE_REQUEST_CODE) {
            loadInsurancesList()
        }
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(ProfileInsuranceListSideEffects.Error(errorSideEffect))
    }
}


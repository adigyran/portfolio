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
    private val coordinatorRouter: CoordinatorRouter,
    private val getInsurancesUseCase: GetInsurancesUseCase,
    private val deleteInsuranceUseCase: DeleteInsuranceUseCase,
) :
    BaseViewModel<ProfilePrescriptionsListState, ProfilePrescriptionsListSideEffects>() {
    override val container = container<ProfilePrescriptionsListState, ProfilePrescriptionsListSideEffects>(
        initialState = ProfilePrescriptionsListState(),
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
            ProfilePrescriptionsListNavigationEvents.EditPrescriptions(
                RequestCodes.EDIT_INSURANCE_REQUEST_CODE,
                id
            )
        )
    }

    fun addInsuranceClicked() = intent {
        listenForInsuranceAddEvent()
        coordinatorRouter.sendEvent(ProfilePrescriptionsListNavigationEvents.AddPrescriptions(RequestCodes.ADD_INSURANCE_REQUEST_CODE))
    }

    fun insuranceItemMoreClicked(id: Int) = intent {
        postSideEffect(ProfilePrescriptionsListSideEffects.ShowPrescriptionsActionsDialog(id))
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
        postSideEffect(ProfilePrescriptionsListSideEffects.Error(errorSideEffect))
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }
}


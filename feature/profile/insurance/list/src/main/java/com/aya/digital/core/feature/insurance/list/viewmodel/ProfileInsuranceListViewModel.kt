package com.aya.digital.core.feature.insurance.list.viewmodel

import com.aya.digital.core.domain.profile.insurance.DeleteInsuranceUseCase
import com.aya.digital.core.domain.profile.insurance.GetInsurancesUseCase
import com.aya.digital.core.feature.insurance.list.navigation.ProfileInsuranceListNavigationEvents
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.rx3.asFlow
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class ProfileInsuranceListViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val getInsurancesUseCase: GetInsurancesUseCase,
    private val deleteInsuranceUseCase: DeleteInsuranceUseCase,
) :
    BaseViewModel<ProfileInsuranceListState, BaseSideEffect>() {
    override val container = container<ProfileInsuranceListState, BaseSideEffect>(
        initialState = ProfileInsuranceListState(),
    )
    {
        loadInsurancesList()
    }

    private fun loadInsurancesList() = intent(registerIdling = false) {
        getInsurancesUseCase().asFlow().collect{result->
            result.processResult({},{Timber.d(it.toString())})
        }
       /* getInsurancesUseCase().await()
            .processResult({ insuranceModels -> reduce { state.copy(insuranceModels = insuranceModels) } },
                { Timber.d(it.toString()) })*/
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

    }

    fun deleteInsurance(id: Int) = intent {
        deleteInsuranceUseCase(id).await()
            .processResult({
                loadInsurancesList()
            }, { Timber.d(it.toString()) })
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

}


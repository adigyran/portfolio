package com.aya.digital.core.feature.insurance.list.viewmodel

import com.aya.digital.core.data.base.result.models.dictionaries.MultiSelectResultModel
import com.aya.digital.core.domain.profile.insurance.DeleteInsuranceUseCase
import com.aya.digital.core.domain.profile.insurance.GetInsurancesUseCase
import com.aya.digital.core.feature.insurance.list.navigation.ProfileInsuranceDoctorNavigationEvents
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

class ProfileInsuranceDoctorViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val getInsurancesUseCase: GetInsurancesUseCase,
    private val deleteInsuranceUseCase: DeleteInsuranceUseCase,
    private val rootCoordinatorRouter: CoordinatorRouter,
) :
    BaseViewModel<ProfileInsuranceDoctorState, ProfileInsuranceDoctorSideEffects>() {
    override val container = container<ProfileInsuranceDoctorState, ProfileInsuranceDoctorSideEffects>(
        initialState = ProfileInsuranceDoctorState(),
    )
    {
      //  loadInsurancesList()
    }

 /*   private fun loadInsurancesList() = intent(registerIdling = false) {
        getInsurancesUseCase().asFlow().collect { result ->
            result.processResult({ models ->
                reduce { state.copy(insurances = models) }
            }, { processError(it) })
        }
    }*/

    private fun selectInsuranceCompany() = intent {
        listenForInsuranceCompany()
        coordinatorRouter.sendEvent(
            ProfileInsuranceDoctorNavigationEvents.SelectInsuranceCompanies(
                RequestCodes.INSURANCE_LIST_REQUEST_CODE,
                state.insurances
            )
        )
    }


    private fun listenForInsuranceCompany() = intent {
        rootCoordinatorRouter.setResultListener(RequestCodes.INSURANCE_LIST_REQUEST_CODE) { result ->
            if (result is MultiSelectResultModel && result.selectedItems.isNotEmpty()) {
               // setInsuranceCompany(result.selectedItems.map { it.id }.first())

            }
        }
    }
    fun insuranceItemClicked() = intent {
        selectInsuranceCompany()
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(ProfileInsuranceDoctorSideEffects.Error(errorSideEffect))
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }
}


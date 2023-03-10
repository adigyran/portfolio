package com.aya.digital.core.feature.profile.insurance.add.viewmodel

import com.aya.digital.core.domain.profile.insurance.AddInsuranceUseCase
import com.aya.digital.core.domain.profile.insurance.DeleteInsuranceUseCase
import com.aya.digital.core.domain.profile.insurance.model.InsuranceAddModel
import com.aya.digital.core.feature.profile.insurance.add.FieldsTags
import com.aya.digital.core.feature.profile.insurance.add.navigation.ProfileInsuranceAddNavigationEvents
import com.aya.digital.core.feature.profile.insurance.add.ui.ProfileInsuranceAddView
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class ProfileInsuranceAddViewModel(
    private val param: ProfileInsuranceAddView.Param,
    private val coordinatorRouter: CoordinatorRouter,
    private val addInsuranceUseCase: AddInsuranceUseCase,
    private val deleteInsuranceUseCase: DeleteInsuranceUseCase
) :
    BaseViewModel<ProfileInsuranceAddState, ProfileInsuranceAddSideEffects>() {
    override val container = container<ProfileInsuranceAddState, ProfileInsuranceAddSideEffects>(
        initialState = ProfileInsuranceAddState(),
    )
    {

    }

    fun nameFieldChanged(tag: Int, text: String) = intent {
        when(tag)
        {
            FieldsTags.NUMBER_FIELD_TAG -> {reduce { state.copy(number = text) }}
        }
    }

    fun onSelectionFieldClicked(tag: Int) = intent {
        when(tag)
        {
            FieldsTags.NAME_FIELD_TAG -> {selectInsuranceCompany()}
        }

    }

    private fun selectInsuranceCompany() {

    }

    fun photoClicked() = intent {

    }

    fun photoMoreClicked() = intent {

    }

    private fun saveInsurance() = intent {
        if (state.photo == null || state.name == null || state.number == null) return@intent
        val insuranceAddModel = InsuranceAddModel(state.photo!!, state.name!!, state.number!!)
        val await = addInsuranceUseCase(insuranceAddModel).await()
        await.processResult({
            //  coordinatorRouter.sendEvent(ProfileInsuranceAddNavigationEvents.FinishWithResult())
        }, { Timber.d(it.toString()) })
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect)= intent {
        postSideEffect(ProfileInsuranceAddSideEffects.Error(errorSideEffect))
    }
}


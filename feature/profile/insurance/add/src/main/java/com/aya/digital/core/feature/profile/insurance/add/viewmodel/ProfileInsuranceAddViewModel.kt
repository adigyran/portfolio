package com.aya.digital.core.feature.profile.insurance.add.viewmodel

import com.aya.digital.core.data.base.dataprocessing.mapResult
import com.aya.digital.core.domain.profile.insurance.AddInsuranceUseCase
import com.aya.digital.core.domain.profile.insurance.DeleteInsuranceUseCase
import com.aya.digital.core.domain.profile.insurance.GetInsuranceByIdUseCase
import com.aya.digital.core.domain.profile.insurance.SaveInsuranceUseCase
import com.aya.digital.core.domain.profile.insurance.model.InsuranceAddModel
import com.aya.digital.core.domain.profile.insurance.model.InsuranceSaveModel
import com.aya.digital.core.feature.profile.insurance.add.FieldsTags
import com.aya.digital.core.feature.profile.insurance.add.ui.ProfileInsuranceAddView
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProfileInsuranceAddViewModel(
    private val param: ProfileInsuranceAddView.Param,
    private val coordinatorRouter: CoordinatorRouter,
    private val getInsuranceCompanyByIdUseCase: GetInsuranceByIdUseCase,
    private val getInsuranceByIdUseCase: GetInsuranceByIdUseCase,
    private val addInsuranceUseCase: AddInsuranceUseCase,
    private val saveInsuranceUseCase: SaveInsuranceUseCase,
    private val deleteInsuranceUseCase: DeleteInsuranceUseCase
    ) :
    BaseViewModel<ProfileInsuranceAddState, ProfileInsuranceAddSideEffects>() {
    override val container = container<ProfileInsuranceAddState, ProfileInsuranceAddSideEffects>(
        initialState = ProfileInsuranceAddState(id = param.insuranceId),
    )
    {
        if (it.id != null) getInsurance()
    }


    private fun getInsurance() = intent {
        if (state.id == null) return@intent
        getInsuranceByIdUseCase(state.id!!).await()
            .processResult({
                reduce {
                    state.copy(
                        number = it.number,
                        organisationId = it.organisationId,
                        organisationName = it.organisationName,
                        photo = it.photoAttachment
                    )
                }
            }, { processError(it) })
    }

    private fun getInsuranceCompanyName() = intent {
        if(state.organisationId==null) return@intent
        getInsuranceCompanyByIdUseCase(state.organisationId!!).await()
            .processResult({
                           reduce { state.copy(organisationName = it.organisationName) }
            },{processError(it)})
    }

    fun nameFieldChanged(tag: Int, text: String) = intent {
        when (tag) {
            FieldsTags.NUMBER_FIELD_TAG -> {
                reduce { state.copy(number = text) }
            }
        }
    }

    fun onSelectionFieldClicked(tag: Int) = intent {
        when (tag) {
            FieldsTags.NAME_FIELD_TAG -> {
                selectInsuranceCompany()
            }
        }

    }

    fun onSaveAddClicked() = intent {
        if (state.id != null) saveInsurance() else addInsurance()
    }


    private fun selectInsuranceCompany() = intent {

    }

    fun photoClicked() = intent {

    }

    fun photoMoreClicked() = intent {

    }

    fun deleteInsurance() = intent {
        if (state.id == null) return@intent
        deleteInsuranceUseCase(state.id!!).await()
            .processResult({
            }, { processError(it) })
    }

    private fun addInsurance() = intent {
        if (state.photo == null || state.organisationName == null || state.number == null) return@intent
        val insuranceAddModel =
            InsuranceAddModel(state.photo!!, state.organisationId!!, state.number!!)
        val await = addInsuranceUseCase(insuranceAddModel).await()
        await.processResult({
            //  coordinatorRouter.sendEvent(ProfileInsuranceAddNavigationEvents.FinishWithResult())
        }, { processError(it) })
    }

    private fun saveInsurance() = intent {
        if (state.id == null || state.photo == null || state.organisationName == null || state.number == null) return@intent
        val insuranceSaveModel =
            InsuranceSaveModel(state.id!!, state.photo!!, state.organisationId!!, state.number!!)
        val await = saveInsuranceUseCase(insuranceSaveModel).await()
        await.processResult({
            //  coordinatorRouter.sendEvent(ProfileInsuranceAddNavigationEvents.FinishWithResult())
        }, { processError(it) })
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(ProfileInsuranceAddSideEffects.Error(errorSideEffect))
    }
}


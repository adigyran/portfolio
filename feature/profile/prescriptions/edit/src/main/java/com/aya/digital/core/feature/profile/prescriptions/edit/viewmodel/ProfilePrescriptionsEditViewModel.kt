package com.aya.digital.core.feature.profile.prescriptions.edit.viewmodel

import android.net.Uri
import com.aya.digital.core.data.base.result.models.dictionaries.MultiSelectResultModel
import com.aya.digital.core.data.base.result.models.insurance.AddInsuranceResultModel
import com.aya.digital.core.domain.dictionaries.insurancecompany.GetInsuranceCompanyItemByIdUseCase
import com.aya.digital.core.domain.profile.insurance.*
import com.aya.digital.core.domain.profile.insurance.model.InsuranceAddModel
import com.aya.digital.core.domain.profile.insurance.model.InsuranceSaveModel
import com.aya.digital.core.feature.profile.prescriptions.edit.FieldsTags
import com.aya.digital.core.feature.profile.prescriptions.edit.navigation.ProfilePrescriptionsEditNavigationEvents
import com.aya.digital.core.feature.profile.prescriptions.edit.ui.ProfileInsuranceAddView
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProfilePrescriptionsEditViewModel(
    private val param: ProfileInsuranceAddView.Param,
    private val coordinatorRouter: CoordinatorRouter,
    private val rootCoordinatorRouter: CoordinatorRouter,
    private val getInsuranceCompanyByIdUseCase: GetInsuranceCompanyItemByIdUseCase,
    private val getInsuranceByIdUseCase: GetInsuranceByIdUseCase,
    private val uploadInsuranceAttachmentUseCase: UploadInsuranceAttachmentUseCase,
    private val addInsuranceUseCase: AddInsuranceUseCase,
    private val saveInsuranceUseCase: SaveInsuranceUseCase,
    private val deleteInsuranceUseCase: DeleteInsuranceUseCase,
    private val getInsuranceAttachmentByIdUseCase: GetInsuranceAttachmentByIdUseCase
) :
    BaseViewModel<ProfilePrescriptionsEditState, ProfilePrescriptionsEditSideEffects>() {
    override val container = container<ProfilePrescriptionsEditState, ProfilePrescriptionsEditSideEffects>(
        initialState = ProfilePrescriptionsEditState(id = param.insuranceId),
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
                        photoLink = it.attachmentUrl
                    )
                }
            }, { processError(it) })
    }

    private fun getInsuranceCompanyName() = intent {
        if (state.organisationId == null) return@intent
        getInsuranceCompanyByIdUseCase(state.organisationId!!).await()
            .processResult({
                reduce { state.copy(organisationName = it.name) }
            }, { processError(it) })
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
        listenForInsuranceCompany()
        coordinatorRouter.sendEvent(
            ProfilePrescriptionsEditNavigationEvents.SelectInsuranceCompany(
                RequestCodes.INSURANCE_LIST_REQUEST_CODE,
                state.organisationId
            )
        )
    }

    fun onFullScreenPhotoClicked() = intent {
        postSideEffect(ProfilePrescriptionsEditSideEffects.HideFullScreenPolicy)

    }
    fun photoClicked() = intent {
        postSideEffect(ProfilePrescriptionsEditSideEffects.ShowFullScreenPolicy)
    }

    fun uploadPhotoClicked() = intent {
        postSideEffect(ProfilePrescriptionsEditSideEffects.SelectImage)
    }

    fun photoMoreClicked() = intent {
        postSideEffect(ProfilePrescriptionsEditSideEffects.ShowInsuranceActionsDialog)
    }

    fun deleteInsurance() = intent {
        if (state.id == null) return@intent
        deleteInsuranceUseCase(state.id!!).await()
            .processResult({
                coordinatorRouter.sendEvent(
                    ProfilePrescriptionsEditNavigationEvents.FinishWithResult(
                        param.requestCode,
                        AddInsuranceResultModel(false)
                    )
                )
            }, { processError(it) })
    }

    private fun addInsurance() = intent {
        if (state.photo == null || state.organisationName == null || state.number == null) return@intent
        val insuranceAddModel =
            InsuranceAddModel(state.photo!!, state.organisationId!!, state.number!!)
        val await = addInsuranceUseCase(insuranceAddModel).await()
        await.processResult({
            coordinatorRouter.sendEvent(
                ProfilePrescriptionsEditNavigationEvents.FinishWithResult(
                    param.requestCode,
                    AddInsuranceResultModel(true)
                )
            )
        }, { processError(it) })
    }

    private fun saveInsurance() = intent {
        if (state.id == null || state.organisationName == null || state.number == null) return@intent
        val insuranceSaveModel =
            InsuranceSaveModel(state.id!!,state.photo, state.organisationId!!, state.number!!)
        val await = saveInsuranceUseCase(insuranceSaveModel).await()
        await.processResult({
            coordinatorRouter.sendEvent(
                ProfilePrescriptionsEditNavigationEvents.FinishWithResult(
                    param.requestCode,
                    AddInsuranceResultModel(true)
                )
            )
        }, { processError(it) })
    }


    private fun listenForInsuranceCompany() = intent {
        rootCoordinatorRouter.setResultListener(RequestCodes.INSURANCE_LIST_REQUEST_CODE) { result ->
            if (result is MultiSelectResultModel && result.selectedItems.isNotEmpty()) {
                setInsuranceCompany(result.selectedItems.map { it.id }.first())

            }
        }
    }

    private fun setInsuranceCompany(companyId: Int) = intent {
        reduce {
            state.copy(
                organisationId = companyId
            )
        }
        getInsuranceCompanyName()
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(ProfilePrescriptionsEditSideEffects.Error(errorSideEffect))
    }

    fun imageSelected(imageUri: Uri) = intent(registerIdling = false) {
        uploadInsuranceAttachmentUseCase(imageUri).await()
            .processResult({ uploadModel ->
                if (uploadModel.uploaded) {
                    reduce {
                        state.copy(photo = uploadModel.id, photoLink = uploadModel.link)
                    }
                }
            }, { processError(it) })
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }
}


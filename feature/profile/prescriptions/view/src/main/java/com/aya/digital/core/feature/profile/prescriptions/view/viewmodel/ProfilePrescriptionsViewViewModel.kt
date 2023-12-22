package com.aya.digital.core.feature.profile.prescriptions.view.viewmodel

import android.net.Uri
import com.aya.digital.core.data.base.result.models.dictionaries.MultiSelectResultModel
import com.aya.digital.core.data.base.result.models.insurance.AddInsuranceResultModel
import com.aya.digital.core.domain.dictionaries.insurancecompany.GetInsuranceCompanyItemByIdUseCase
import com.aya.digital.core.domain.profile.insurance.*
import com.aya.digital.core.domain.profile.insurance.model.InsuranceAddModel
import com.aya.digital.core.domain.profile.insurance.model.InsuranceSaveModel
import com.aya.digital.core.feature.profile.prescriptions.view.FieldsTags
import com.aya.digital.core.feature.profile.prescriptions.view.navigation.ProfilePrescriptionsViewNavigationEvents
import com.aya.digital.core.feature.profile.prescriptions.view.ui.ProfileInsuranceAddView
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProfilePrescriptionsViewViewModel(
    private val param: ProfileInsuranceAddView.Param,
    private val coordinatorRouter: CoordinatorRouter,
    private val rootCoordinatorRouter: CoordinatorRouter
) :
    BaseViewModel<ProfilePrescriptionsViewState, ProfilePrescriptionsViewSideEffects>() {
    override val container = container<ProfilePrescriptionsViewState, ProfilePrescriptionsViewSideEffects>(
        initialState = ProfilePrescriptionsViewState(),
    )
    {

    }



    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(ProfilePrescriptionsViewSideEffects.Error(errorSideEffect))
    }


    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }
}


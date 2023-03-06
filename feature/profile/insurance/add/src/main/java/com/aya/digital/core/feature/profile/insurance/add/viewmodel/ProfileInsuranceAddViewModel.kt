package com.aya.digital.core.feature.profile.insurance.add.viewmodel

import com.aya.digital.core.feature.profile.insurance.add.FieldsTags
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProfileInsuranceAddViewModel(
    private val coordinatorRouter: CoordinatorRouter,) :
    BaseViewModel<ProfileInsuranceAddState, BaseSideEffect>() {
    override val container = container<ProfileInsuranceAddState, BaseSideEffect>(
        initialState = ProfileInsuranceAddState(),
    )
    {

    }

    fun nameFieldChanged(tag:Int, text:String) = intent {

    }

    fun photoClicked() = intent {

    }

    fun photoMoreClicked() = intent {

    }

    fun saveInsurance() = intent {

    }

}


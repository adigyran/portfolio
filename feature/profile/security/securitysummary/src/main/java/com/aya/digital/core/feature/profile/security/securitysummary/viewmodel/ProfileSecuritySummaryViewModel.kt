package com.aya.digital.core.feature.profile.security.securitysummary.viewmodel

import com.aya.digital.core.feature.profile.security.securitysummary.FieldsTags
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProfileSecuritySummaryViewModel(
    private val coordinatorRouter: CoordinatorRouter
) :
    BaseViewModel<ProfileSecuritySummaryState, BaseSideEffect>() {
    override val container = container<ProfileSecuritySummaryState, BaseSideEffect>(
        initialState = ProfileSecuritySummaryState(),
    )
    {

    }

    fun itemClicked(tag:Int) = intent {

    }


}


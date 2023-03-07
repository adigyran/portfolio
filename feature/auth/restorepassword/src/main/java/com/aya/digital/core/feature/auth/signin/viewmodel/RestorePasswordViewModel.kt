package com.aya.digital.core.feature.auth.signin.viewmodel

import com.aya.digital.core.domain.auth.SignInUseCase
import com.aya.digital.core.feature.auth.signin.FieldsTags
import com.aya.digital.core.feature.auth.signin.navigation.RestorePasswordNavigationEvents
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class RestorePasswordViewModel(
    private val coordinatorRouter: CoordinatorRouter
) :
    BaseViewModel<RestorePasswordState, BaseSideEffect>() {
    override val container = container<RestorePasswordState, BaseSideEffect>(
        initialState = RestorePasswordState(),
    )
    {

    }

}


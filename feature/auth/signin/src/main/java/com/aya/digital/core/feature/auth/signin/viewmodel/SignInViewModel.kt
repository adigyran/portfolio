package com.aya.digital.core.feature.auth.signin.viewmodel

import android.util.Log
import com.aya.digital.core.domain.auth.SignInUseCase
import com.aya.digital.core.feature.auth.signin.FieldsTags
import com.aya.digital.core.feature.auth.signin.navigation.SignInNavigationEvents
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class SignInViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val signInUseCase: SignInUseCase
) :
    BaseViewModel<SignInState, BaseSideEffect>() {
    override val container = container<SignInState, BaseSideEffect>(
        initialState = SignInState(),
    )
    {

    }

    fun emailChanged(email: String) = intent {
        if (state.email != email) reduce { state.copy(email = email) }
    }

    fun passwordChanged(tag: Int, password: String) = intent {
        if (tag == FieldsTags.PASSWORD_FIELD_TAG) {
            if (state.password != password) reduce { state.copy(password = password) }
        }
    }

    fun onSignInClicked() = intent {
        if (state.email.isNotBlank() && state.password.isNotBlank()) {
            val signIn = signInUseCase(state.email, state.password).await()
            signIn.processResult({ coordinatorRouter.sendEvent(SignInNavigationEvents.SignedIn) },
                { error -> Timber.e(error.toString()) })
        }
    }

    fun onSignUpClicked() = intent {
        coordinatorRouter.sendEvent(SignInNavigationEvents.SignUp)
    }

}


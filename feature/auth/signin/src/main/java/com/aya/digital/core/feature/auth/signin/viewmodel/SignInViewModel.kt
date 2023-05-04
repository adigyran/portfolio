package com.aya.digital.core.feature.auth.signin.viewmodel

import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.viewModelScope
import com.aya.digital.core.data.base.result.models.auth.PasswordRestoreResultModel
import com.aya.digital.core.domain.auth.signin.PerformTokenRequestOAuthUseCase
import com.aya.digital.core.domain.auth.signin.SignInOAuthUseCase
import com.aya.digital.core.domain.auth.signin.SignInUseCase
import com.aya.digital.core.feature.auth.signin.FieldsTags
import com.aya.digital.core.feature.auth.signin.navigation.SignInNavigationEvents
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.launch
import kotlinx.coroutines.rx3.await
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.TokenRequest
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class SignInViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val rootCoordinatorRouter: CoordinatorRouter,
    private val authService: AuthorizationService,
    private val signInUseCase: SignInUseCase,
    private val signInOAuthUseCase: SignInOAuthUseCase,
    private val performTokenRequestOAuthUseCase: PerformTokenRequestOAuthUseCase
) :
    BaseViewModel<SignInState, SignInSideEffects>() {
    override val container = container<SignInState, SignInSideEffects>(
        initialState = SignInState(),
    )
    {

    }

    fun loginCanceled() {
        //go again
        onSignInOathClicked()
    }

    fun onAuthCodeFailed(exception: AuthorizationException) {
        Timber.tag("Oauth").d("ERRROORR $exception")
        //TODO handle errors
    }

    fun onAuthCodeReceived(tokenRequest: TokenRequest) {

        Timber.tag("Oauth").d("3. Received code = ${tokenRequest.authorizationCode}")

        viewModelScope.launch {
            runCatching {
                Timber.tag("Oauth")
                    .d("4. Change code to token. Url = ${tokenRequest.configuration.tokenEndpoint}, verifier = ${tokenRequest.codeVerifier}")
                performTokenRequestOAuthUseCase(
                    authService = authService,
                    tokenRequest = tokenRequest
                )
                coordinatorRouter.sendEvent(SignInNavigationEvents.SignedIn)
            }
        }
    }

    fun emailChanged(email: String) = intent {
        if (state.email != email) reduce { state.copy(email = email) }
    }

    fun passwordChanged(tag: Int, password: String) = intent {
        if (tag == FieldsTags.PASSWORD_FIELD_TAG) {
            if (state.password != password) reduce { state.copy(password = password) }
        }
    }

    fun onSignInOathClicked() = intent {
        signInOAuthUseCase().await()
            .processResult({ openLoginPage(it) }, { processError(it) })
    }

    private fun openLoginPage(authRequest: AuthorizationRequest) = intent {
        val customTabsIntent = CustomTabsIntent.Builder().build()
        val openAuthPageIntent = authService.getAuthorizationRequestIntent(
            authRequest,
            customTabsIntent
        )
        postSideEffect(SignInSideEffects.OpenOAuthPage(openAuthPageIntent))
    }

    fun onSignInClicked() = intent {
        if (state.email.isNotBlank() && state.password.isNotBlank()) {
            val signIn = signInUseCase(state.email, state.password).await()
            signIn.processResult({ coordinatorRouter.sendEvent(SignInNavigationEvents.SignedIn) },
                { error -> processError(error) })
        }
    }

    fun onSignUpClicked() = intent {
        coordinatorRouter.sendEvent(SignInNavigationEvents.SignUp)
    }

    fun restorePassword() = intent {
        listenForRestorePasswordEvent()
        coordinatorRouter.sendEvent(SignInNavigationEvents.RestorePassword(RequestCodes.RESTORE_PASSWORD_REQUEST_CODE))
    }

    private fun listenForRestorePasswordEvent() {
        rootCoordinatorRouter.setResultListener(RequestCodes.RESTORE_PASSWORD_REQUEST_CODE) {
            if (it is PasswordRestoreResultModel) {
                passwordRestored(it.passwordRestored)
            }
        }
    }

    private fun passwordRestored(passwordRestored: Boolean) = intent {
        if (!passwordRestored) return@intent
        reduce { state.copy(password = "", passwordError = null) }
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(SignInSideEffects.Error(errorSideEffect))
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }

}


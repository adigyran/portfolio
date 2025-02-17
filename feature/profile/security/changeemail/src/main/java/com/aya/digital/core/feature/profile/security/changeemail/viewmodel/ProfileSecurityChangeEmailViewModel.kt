package com.aya.digital.core.feature.profile.security.changeemail.viewmodel

import com.aya.digital.core.data.base.result.models.code.CodeResultModel
import com.aya.digital.core.domain.profile.security.changeemail.ChangeEmailGetCodeUseCase
import com.aya.digital.core.domain.profile.security.changeemail.ChangeEmailUseCase
import com.aya.digital.core.feature.profile.security.changeemail.navigation.ProfileSecurityChangeEmailNavigationEvents
import com.aya.digital.core.feature.profile.security.changeemail.ui.ProfileSecurityChangeEmailView
import com.aya.digital.core.localisation.R
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.ui.base.validation.MailValidator
import com.aya.digital.core.ui.base.validation.NotEmptyValidator
import com.aya.digital.core.ui.base.validation.ValidationResult.Ok.processResultMessage
import com.aya.digital.core.ui.base.validation.validateField
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

internal class ProfileSecurityChangeEmailViewModel(
    private val param: ProfileSecurityChangeEmailView.Param,
    private val rootCoordinatorRouter: CoordinatorRouter,
    private val coordinatorRouter: CoordinatorRouter,
    private val changeEmailUseCase: ChangeEmailUseCase,
    private val changeEmailGetCodeUseCase: ChangeEmailGetCodeUseCase
) :
    BaseViewModel<ProfileSecurityChangeEmailState, ProfileSecurityChangeEmailSideEffects>() {
    override val container =
        container<ProfileSecurityChangeEmailState, ProfileSecurityChangeEmailSideEffects>(
            initialState = ProfileSecurityChangeEmailState(),
        )
        {

        }
    private val emailValidator = MailValidator(R.string.no_role_defined_error)
    private val notEmptyValidator = NotEmptyValidator(R.string.no_role_defined_error)

    private fun changeEmail() = intent {
        state.code?.let { code ->
            val await = changeEmailUseCase(code = code, newEmail = state.email).await()
            await.processResult({}, { processError(it) })
        }
    }

    private fun requestCode() = intent {
        val await = changeEmailGetCodeUseCase().await()
        await.processResult({
            requestCodeScreen()
        }, { processError(it) })
    }

    private fun requestCodeScreen() = intent {
        listenForCodeEvent()
        coordinatorRouter.sendEvent(
            ProfileSecurityChangeEmailNavigationEvents.EnterCode(
                RequestCodes.CODE_INPUT_REQUEST_CODE_EMAIL,
                state.email
            )
        )
    }

    private fun listenForCodeEvent() {
        rootCoordinatorRouter.setResultListener(RequestCodes.CODE_INPUT_REQUEST_CODE_EMAIL) {
            if (it is CodeResultModel) {
                codeEntered(it.code)
            }
        }
    }

    private fun codeEntered(code: String) = intent {
        reduce {
            state.copy(code = code)
        }
        if (code.length == 6) changeEmail()

    }

    fun saveClicked() = intent {
        validateAllFields {
            if (!it) return@validateAllFields
            requestCode()
        }

    }

    fun emailChanged(tag: Int, email: String) = intent {
        if (state.email != email) reduce { state.copy(email = email) }
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(ProfileSecurityChangeEmailSideEffects.Error(errorSideEffect))
    }

    private fun String.validateEmail() =
        this.validateField(false, notEmptyValidator, emailValidator)

    private fun validateAllFields(callback: (Boolean) -> Unit) =
        intent {
            val emailValidation = state.email.validateEmail()
            reduce { state.copy(emailError = emailValidation.processResultMessage()) }
            callback(emailValidation.isOk())
        }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }
}


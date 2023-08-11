package com.aya.digital.core.feature.profile.security.changepassword.viewmodel

import com.aya.digital.core.domain.profile.security.changepassword.ChangePasswordUseCase
import com.aya.digital.core.domain.profile.security.changepassword.model.ChangePasswordModel
import com.aya.digital.core.feature.profile.security.changepassword.FieldsTags
import com.aya.digital.core.feature.profile.security.changepassword.ui.ProfileSecurityChangePasswordView
import com.aya.digital.core.localisation.R
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.ui.base.validation.NotEmptyValidator
import com.aya.digital.core.ui.base.validation.PasswordRepeatValidator
import com.aya.digital.core.ui.base.validation.PasswordValidator
import com.aya.digital.core.ui.base.validation.ValidationResult.Ok.processResultMessage
import com.aya.digital.core.ui.base.validation.validateField
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

internal class ProfileSecurityChangePasswordViewModel(
    private val param: ProfileSecurityChangePasswordView.Param,
    private val coordinatorRouter: CoordinatorRouter,
    private val changePasswordUseCase: ChangePasswordUseCase
) :
    BaseViewModel<ProfileSecurityChangePasswordState, ProfileSecurityChangePasswordSideEffects>() {
    override val container =
        container<ProfileSecurityChangePasswordState, ProfileSecurityChangePasswordSideEffects>(
            initialState = ProfileSecurityChangePasswordState(),
        )
        {

        }

    private val notEmptyValidator = NotEmptyValidator(R.string.no_role_defined_error)
    private val passwordValidator = PasswordValidator(R.string.no_role_defined_error)
    private val passwordRepeatValidator = PasswordRepeatValidator(R.string.no_role_defined_error)

    fun passwordFieldChanged(tag: Int, password: String) = intent {
        when (tag) {
            FieldsTags.CURRENT_PASSWORD_FIELD_TAG -> {
                reduce { state.copy(currentPassword = password) }
            }

            FieldsTags.NEW_PASSWORD_FIELD_TAG -> {
                reduce { state.copy(newPassword = password) }
            }

            FieldsTags.NEW_REPEAT_PASSWORD_FIELD_TAG -> {
                reduce { state.copy(newRepeatPassword = password) }
            }
        }
    }

    fun onChangePassword() = intent {
        validateAllFields {
            if (!it) return@validateAllFields
            changePassword()
        }
    }

    private fun changePassword() = intent {
        val changePasswordModel =
            ChangePasswordModel(state.currentPassword, state.newPassword, state.newRepeatPassword)
        changePasswordUseCase(changePasswordModel).await()
            .processResult({}, { processError(it) })
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(ProfileSecurityChangePasswordSideEffects.Error(errorSideEffect))
    }

    private fun String.validatePassword() =
        this.validateField(false, notEmptyValidator, passwordValidator)

    private fun Pair<String, String>.validateRepeatPassword() =
        this.validateField(false, passwordRepeatValidator)

    private fun validateAllFields(callback: (Boolean) -> Unit) = intent {
        val currentPasswordValidation = state.currentPassword.validatePassword()
        val newPasswordValidation = state.newPassword.validatePassword()
        val newPasswordRepeatValidation =
            Pair(state.newPassword, state.newRepeatPassword).validateRepeatPassword()
        reduce { state.copy(
            currentPasswordError = currentPasswordValidation.processResultMessage(),
            newPasswordError = newPasswordValidation.processResultMessage(),
            newRepeatPasswordError = newPasswordRepeatValidation.processResultMessage()
        ) }
        callback(currentPasswordValidation.isOk() && newPasswordValidation.isOk() && newPasswordRepeatValidation.isOk())
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }


}


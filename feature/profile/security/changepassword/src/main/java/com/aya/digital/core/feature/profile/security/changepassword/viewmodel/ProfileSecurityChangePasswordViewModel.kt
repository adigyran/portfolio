package com.aya.digital.core.feature.profile.security.changepassword.viewmodel

import com.aya.digital.core.domain.profile.security.changepassword.ChangePasswordUseCase
import com.aya.digital.core.domain.profile.security.changepassword.model.ChangePasswordModel
import com.aya.digital.core.feature.profile.security.changepassword.FieldsTags
import com.aya.digital.core.feature.profile.security.changepassword.ui.ProfileSecurityChangePasswordView
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

internal class ProfileSecurityChangePasswordViewModel(
    private val param: ProfileSecurityChangePasswordView.Param,
    private val coordinatorRouter: CoordinatorRouter,
    private val changePasswordUseCase: ChangePasswordUseCase
) :
    BaseViewModel<ProfileSecurityChangePasswordState, ProfileSecurityChangePasswordSideEffects>() {
    override val container = container<ProfileSecurityChangePasswordState, ProfileSecurityChangePasswordSideEffects>(
        initialState = ProfileSecurityChangePasswordState(),
    )
    {

    }

    fun passwordFieldChanged(tag: Int, password: String) = intent {
        when (tag) {
            FieldsTags.CURRENT_PASSWORD_FIELD_TAG -> {reduce { state.copy(currentPassword = password) }}
            FieldsTags.NEW_PASSWORD_FIELD_TAG -> {reduce { state.copy(newPassword = password) }}
            FieldsTags.NEW_REPEAT_PASSWORD_FIELD_TAG -> {reduce { state.copy(newRepeatPassword = password) }}
        }
    }

    fun changePassword() = intent {
        val changePasswordModel =
            ChangePasswordModel(state.currentPassword, state.newPassword, state.newRepeatPassword)
        changePasswordUseCase(changePasswordModel).await()
            .processResult({}, { processError(it) })
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(ProfileSecurityChangePasswordSideEffects.Error(errorSideEffect))
    }
}


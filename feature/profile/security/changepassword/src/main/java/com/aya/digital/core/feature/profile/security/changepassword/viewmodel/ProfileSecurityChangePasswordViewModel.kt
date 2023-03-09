package com.aya.digital.core.feature.profile.security.changepassword.viewmodel

import com.aya.digital.core.domain.profile.security.changepassword.ChangePasswordUseCase
import com.aya.digital.core.domain.profile.security.changepassword.model.ChangePasswordModel
import com.aya.digital.core.feature.profile.security.changepassword.ui.ProfileSecurityChangePasswordView
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class ProfileSecurityChangePasswordViewModel(
    private val param: ProfileSecurityChangePasswordView.Param,
    private val coordinatorRouter: CoordinatorRouter,
    private val changePasswordUseCase: ChangePasswordUseCase
) :
    BaseViewModel<ProfileSecurityChangePasswordState, BaseSideEffect>() {
    override val container = container<ProfileSecurityChangePasswordState, BaseSideEffect>(
        initialState = ProfileSecurityChangePasswordState(),
    )
    {

    }

    fun passwordFieldChanged(tag: Int, password: String) = intent {
        when (tag) {

        }
    }

    private fun changePassword() = intent {
        val changePasswordModel =
            ChangePasswordModel(state.currentPassword, state.newPassword, state.newRepeatPassword)
        changePasswordUseCase(changePasswordModel).await()
            .processResult({}, { Timber.d(it.toString()) })
    }

}


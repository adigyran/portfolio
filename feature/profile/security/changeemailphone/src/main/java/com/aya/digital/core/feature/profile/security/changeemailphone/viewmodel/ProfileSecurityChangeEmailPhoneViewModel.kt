package com.aya.digital.core.feature.profile.security.changeemailphone.viewmodel

import com.aya.digital.core.domain.profile.security.changeemail.ChangeEmailGetCodeUseCase
import com.aya.digital.core.domain.profile.security.changeemail.ChangeEmailUseCase
import com.aya.digital.core.domain.profile.security.changepassword.ChangePasswordUseCase
import com.aya.digital.core.feature.profile.security.changeemailphone.FieldsTags
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class ProfileSecurityChangeEmailPhoneViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val changeEmailUseCase: ChangeEmailUseCase,
    private val changeEmailGetCodeUseCase: ChangeEmailGetCodeUseCase
) :
    BaseViewModel<ProfileSecurityChangeEmailPhoneState, BaseSideEffect>() {
    override val container = container<ProfileSecurityChangeEmailPhoneState, BaseSideEffect>(
        initialState = ProfileSecurityChangeEmailPhoneState(),
    )
    {

    }

    private fun changeEmail(email: String) = intent {
        state.code?.let { code->
            val await = changeEmailUseCase(code = code, newEmail = email).await()
            await.processResult({},{Timber.d(it.toString())})
        }
    }

    private fun requestCode() = intent {
        val await = changeEmailGetCodeUseCase().await()
        await.processResult({
        },{Timber.d(it.toString())})
    }

    fun emailChanged(email: String) = intent {
        if (state.email != email) reduce { state.copy(email = email) }
    }

}


package com.aya.digital.core.feature.profile.security.changephone.viewmodel

import com.aya.digital.core.data.base.result.models.code.CodeResultModel
import com.aya.digital.core.domain.profile.security.changeemail.ChangeEmailGetCodeUseCase
import com.aya.digital.core.domain.profile.security.changeemail.ChangeEmailUseCase
import com.aya.digital.core.feature.profile.security.changephone.navigation.ProfileSecurityChangePhoneNavigationEvents
import com.aya.digital.core.feature.profile.security.changephone.ui.ProfileSecurityChangePhoneView
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

internal class ProfileSecurityChangePhoneViewModel(
    private val param: ProfileSecurityChangePhoneView.Param,
    private val rootCoordinatorRouter: CoordinatorRouter,
    private val coordinatorRouter: CoordinatorRouter,
    private val changeEmailUseCase: ChangeEmailUseCase,
    private val changeEmailGetCodeUseCase: ChangeEmailGetCodeUseCase
) :
    BaseViewModel<ProfileSecurityChangePhoneState, ProfileSecurityChangePhoneSideEffects>() {
    override val container =
        container<ProfileSecurityChangePhoneState, ProfileSecurityChangePhoneSideEffects>(
            initialState = ProfileSecurityChangePhoneState(),
        )
        {

        }

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
            ProfileSecurityChangePhoneNavigationEvents.EnterCode(
                RequestCodes.CODE_INPUT_REQUEST_CODE,
                state.email
            )
        )
    }

    private fun listenForCodeEvent() {
        rootCoordinatorRouter.setResultListener(RequestCodes.CODE_INPUT_REQUEST_CODE) {
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

    fun saveClicked() = intent { requestCode() }

    fun emailChanged(tag: Int, email: String) = intent {
        if (state.email != email) reduce { state.copy(email = email) }
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(ProfileSecurityChangePhoneSideEffects.Error(errorSideEffect))
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }
}


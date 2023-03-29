package com.aya.digital.core.feature.profile.security.changeemailphone.viewmodel

import com.aya.digital.core.data.base.result.models.code.CodeResultModel
import com.aya.digital.core.domain.profile.security.changeemail.ChangeEmailGetCodeUseCase
import com.aya.digital.core.domain.profile.security.changeemail.ChangeEmailUseCase
import com.aya.digital.core.domain.profile.security.changepassword.ChangePasswordUseCase
import com.aya.digital.core.feature.profile.security.changeemailphone.FieldsTags
import com.aya.digital.core.feature.profile.security.changeemailphone.navigation.ProfileSecurityChangeEmailPhoneNavigationEvents
import com.aya.digital.core.feature.profile.security.changeemailphone.ui.ProfileSecurityChangeEmailPhoneView
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

internal class ProfileSecurityChangeEmailPhoneViewModel(
    private val param: ProfileSecurityChangeEmailPhoneView.Param,
    private val rootCoordinatorRouter: CoordinatorRouter,
    private val coordinatorRouter: CoordinatorRouter,
    private val changeEmailUseCase: ChangeEmailUseCase,
    private val changeEmailGetCodeUseCase: ChangeEmailGetCodeUseCase
) :
    BaseViewModel<ProfileSecurityChangeEmailPhoneState, ProfileSecurityChangeEmailPhoneSideEffects>() {
    override val container =
        container<ProfileSecurityChangeEmailPhoneState, ProfileSecurityChangeEmailPhoneSideEffects>(
            initialState = ProfileSecurityChangeEmailPhoneState(),
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
            ProfileSecurityChangeEmailPhoneNavigationEvents.EnterCode(
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

    fun emailChanged(email: String) = intent {
        if (state.email != email) reduce { state.copy(email = email) }
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(ProfileSecurityChangeEmailPhoneSideEffects.Error(errorSideEffect))
    }


}


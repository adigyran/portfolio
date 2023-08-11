package com.aya.digital.core.feature.profile.security.changephone.viewmodel

import com.aya.digital.core.data.base.result.models.code.CodeResultModel
import com.aya.digital.core.data.base.result.models.profile.ProfileEmailChangeResult
import com.aya.digital.core.data.base.result.models.profile.ProfilePhoneChangeResult
import com.aya.digital.core.domain.profile.security.changephone.ChangePhoneConfirmCodeUseCase
import com.aya.digital.core.domain.profile.security.changephone.ChangePhoneGetCodeUseCase
import com.aya.digital.core.domain.profile.security.changephone.ChangePhoneUseCase
import com.aya.digital.core.domain.profile.security.changephone.GetPhoneVerifiedStatusUseCase
import com.aya.digital.core.feature.profile.security.changephone.navigation.ProfileSecurityChangePhoneNavigationEvents
import com.aya.digital.core.feature.profile.security.changephone.ui.ProfileSecurityChangePhoneView
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

internal class ProfileSecurityChangePhoneViewModel(
    private val param: ProfileSecurityChangePhoneView.Param,
    private val rootCoordinatorRouter: CoordinatorRouter,
    private val coordinatorRouter: CoordinatorRouter,
    private val changePhoneUseCase: ChangePhoneUseCase,
    private val changePhoneGetCodeUseCase: ChangePhoneGetCodeUseCase,
    private val changePhoneConfirmCodeUseCase: ChangePhoneConfirmCodeUseCase,
    private val getPhoneVerifiedStatusUseCase: GetPhoneVerifiedStatusUseCase

    ) :
    BaseViewModel<ProfileSecurityChangePhoneState, ProfileSecurityChangePhoneSideEffects>() {
    override val container =
        container<ProfileSecurityChangePhoneState, ProfileSecurityChangePhoneSideEffects>(
            initialState = ProfileSecurityChangePhoneState(),
        )
        {
            setPhone()
        }

    private fun setPhone() = intent{
        reduce {
            state.copy(
               phone = null
            )
        }

        runBlocking { // this: CoroutineScope
            launch { // launch a new coroutine and continue
                delay(10L) // non-blocking delay for 1 second (default time unit is ms)
                reduce {
                    state.copy(
                        phone = param.phone
                    )
                }
            }
        }
    }


    private fun validatePhone() = intent {
        state.code?.let { code ->
            val await = changePhoneConfirmCodeUseCase(code = code).await()
            await.processResult({result ->
                coordinatorRouter.sendEvent(ProfileSecurityChangePhoneNavigationEvents.FinishWithResult(param.requestCode,
                    ProfilePhoneChangeResult(true)
                ))
            }, { processError(it) })
        }
    }

    private fun savePhone() = intent {
        if(state.phone.isNullOrBlank()) return@intent
        val await = changePhoneUseCase(newPhone = state.phone!!).await()
        await.processResult({
            checkIsValidated()
        }, { processError(it) })
    }


    private fun requestCode() = intent {
        val await = changePhoneGetCodeUseCase().await()
        await.processResult({
            requestCodeScreen()
        }, { processError(it) })
    }

    private fun checkIsValidated() = intent {
        val await = getPhoneVerifiedStatusUseCase().await()
        await.processResult({result->
            if(!result) requestCode()
        }, { processError(it) })
    }

    private fun requestCodeScreen() = intent {
        if(state.phone.isNullOrBlank()) return@intent
        listenForCodeEvent()
        coordinatorRouter.sendEvent(
            ProfileSecurityChangePhoneNavigationEvents.EnterCode(
                RequestCodes.CODE_INPUT_REQUEST_CODE_PHONE,
                state.phone!!
            )
        )
    }

    private fun listenForCodeEvent() {
        rootCoordinatorRouter.setResultListener(RequestCodes.CODE_INPUT_REQUEST_CODE_PHONE) {
            if (it is CodeResultModel) {
                codeEntered(it.code)
            }
        }
    }

    private fun codeEntered(code: String) = intent {
        reduce {
            state.copy(code = code)
        }
        if (code.length == 6) validatePhone()

    }

    fun saveClicked() = intent {
        savePhone() }

    fun phoneChanged(tag: Int, phone: String) = intent {
        reduce { state.copy(phone = phone) }
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(ProfileSecurityChangePhoneSideEffects.Error(errorSideEffect))
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }
}


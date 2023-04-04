package com.aya.digital.core.feature.profile.generalinfo.view.viewmodel

import android.net.Uri
import com.aya.digital.core.domain.profile.generalinfo.edit.SetAvatarUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileInfoUseCase
import com.aya.digital.core.feature.profile.generalinfo.view.navigation.ProfileGeneralInfoViewNavigationEvents
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

class ProfileGeneralInfoViewViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val profileInfoUseCase: GetProfileInfoUseCase,
    private val setAvatarUseCase: SetAvatarUseCase
) :
    BaseViewModel<ProfileGeneralInfoViewState, ProfileGeneralInfoSideEffects>() {
    override val container = container<ProfileGeneralInfoViewState, ProfileGeneralInfoSideEffects>(
        initialState = ProfileGeneralInfoViewState(),
    )
    {

    }

    init {
        loadProfile()
    }

    private fun loadProfile() = intent {
        val resultModel = profileInfoUseCase().await()
        resultModel.processResult({ profileInfo ->
            reduce {
                state.copy(
                    avatar = profileInfo.avatar?:"",
                    firstName = profileInfo.firstName,
                    middleName = profileInfo.middleName,
                    lastName = profileInfo.lastName,
                    shortAddress = profileInfo.shortAddress,
                    dateOfBirth = profileInfo.dateOfBirth,
                    height = profileInfo.height,
                    sex = profileInfo.sex,
                    weight = profileInfo.weight,
                    ssn = profileInfo.ssn ?: profileInfo.tin,
                    tin = profileInfo.tin
                )
            }
        }, { Timber.d(it.toString()) })

    }

    fun avatarSelectClicked() = intent {
        postSideEffect(ProfileGeneralInfoSideEffects.SelectAvatar)
    }
    fun profileAvatarImageSelected(uri: Uri) = intent(registerIdling = false) {
        setAvatarUseCase(uri).await()
            .processResult({loadProfile()},{processError(it)})
    }

    fun onEditClicked() = intent {
        listenForProfileEdit()
        coordinatorRouter.sendEvent(
            ProfileGeneralInfoViewNavigationEvents.EditProfile(
                RequestCodes.EDIT_PROFILE_REQUEST_CODE,
                null
            )
        )
    }

    private fun listenForProfileEdit() {
        coordinatorRouter.setResultListener(RequestCodes.EDIT_PROFILE_REQUEST_CODE) {
            loadProfile()
        }
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(ProfileGeneralInfoSideEffects.Error(errorSideEffect))
    }
}


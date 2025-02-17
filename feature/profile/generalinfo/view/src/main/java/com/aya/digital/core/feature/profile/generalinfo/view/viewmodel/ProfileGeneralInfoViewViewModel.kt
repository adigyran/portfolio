package com.aya.digital.core.feature.profile.generalinfo.view.viewmodel

import android.net.Uri
import com.aya.digital.core.domain.profile.generalinfo.edit.SetAvatarUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileInfoUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.model.FlavoredProfileModel
import com.aya.digital.core.feature.profile.generalinfo.view.navigation.ProfileGeneralInfoViewNavigationEvents
import com.aya.digital.core.feature.profile.generalinfo.view.ui.ProfileGeneralInfoViewView
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.rx3.await
import kotlinx.datetime.toJavaLocalDate
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class ProfileGeneralInfoViewViewModel(
    private val param: ProfileGeneralInfoViewView.Param,
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
                    email = profileInfo.email,
                    firstName = profileInfo.firstName,
                    middleName = profileInfo.middleName,
                    lastName = profileInfo.lastName,
                    dateOfBirth = profileInfo.dateOfBirth
                )
            }
            when (val flavoredProfileModel = profileInfo.flavoredProfileModel) {
                is FlavoredProfileModel.DoctorProfileModel -> reduce {
                    state.copy(
                        doctorFields = DoctorFields(
                            bio = flavoredProfileModel.bio,
                            languages = flavoredProfileModel.languages,
                            specialities = flavoredProfileModel.specialities,
                            medicalDegrees = flavoredProfileModel.degrees
                        )
                    )
                }

                is FlavoredProfileModel.PatientProfileModel -> reduce {state.copy(
                    patientFields = PatientFields(
                        sex = flavoredProfileModel.sex,
                        height = flavoredProfileModel.height,
                        weight = flavoredProfileModel.weight,
                        shortAddress = flavoredProfileModel.shortAddress,
                        ssn = flavoredProfileModel.ssn,
                        tin = flavoredProfileModel.tin
                    )
                ) }
                null -> {}
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

    override fun onBack() {
        coordinatorRouter.sendEvent(ProfileGeneralInfoViewNavigationEvents.FinishWithResult(param.requestCode))
    }
}


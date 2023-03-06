package com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel

import com.aya.digital.core.domain.profile.generalinfo.edit.SaveProfileInfoUseCase
import com.aya.digital.core.domain.profile.generalinfo.edit.model.ProfileEditModel
import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileInfoUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.model.ProfileInfoModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class ProfileGeneralInfoEditViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val profileInfoUseCase: GetProfileInfoUseCase,
    private val saveProfileInfoUseCase: SaveProfileInfoUseCase
) :
    BaseViewModel<ProfileGeneralInfoEditState, BaseSideEffect>() {
    override val container = container<ProfileGeneralInfoEditState, BaseSideEffect>(
        initialState = ProfileGeneralInfoEditState(),
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
                    firstName = profileInfo.firstName,
                    middleName = profileInfo.middleName,
                    lastName = profileInfo.lastName,
                    shortAddress = profileInfo.shortAddress,
                    dateOfBirth = profileInfo.dateOfBirth,
                    height = profileInfo.height,
                    sex = profileInfo.sex,
                    weight = profileInfo.weight

                )
            }
        }, { Timber.d(it.toString()) })

    }

    fun onSaveProfileClicked() = intent {

        val profileEditModel = getProfileEditModel(state)
        profileEditModel.shouldSaveProfile(state)?.let {
            if (it) {
                val await = saveProfileInfoUseCase(profileEditModel).await()
                await.processResult({
                }, { Timber.d(it.toString()) })
            }
        }

    }

    private fun getProfileEditModel(state: ProfileGeneralInfoEditState) =
        ProfileEditModel().apply {
            firstName = state.firstName
            lastName = state.lastName
            middleName = state.middleName
            dateOfBirth = state.dateOfBirth
            sex = state.sex
            height = state.height
            weight = state.weight
            shortAddress = state.shortAddress
        }

    private inline fun <reified T> compareFields(fieldFirst: T?, fieldSecond: T?): Boolean? =
        fieldFirst?.run {
            if (fieldSecond == null) return@run false
            return@run fieldSecond == fieldFirst
        }


    private fun ProfileEditModel.shouldSaveProfile(state: ProfileGeneralInfoEditState): Boolean? {
        return true
    }


}






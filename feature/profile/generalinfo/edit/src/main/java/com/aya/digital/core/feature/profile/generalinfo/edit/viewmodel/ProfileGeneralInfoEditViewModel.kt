package com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel

import android.net.Uri
import com.aya.digital.core.domain.profile.generalinfo.edit.SaveProfileInfoUseCase
import com.aya.digital.core.domain.profile.generalinfo.edit.SetAvatarUseCase
import com.aya.digital.core.domain.profile.generalinfo.edit.model.ProfileEditModel
import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileInfoUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.model.ProfileInfoModel
import com.aya.digital.core.feature.profile.generalinfo.edit.FieldsTags
import com.aya.digital.core.feature.profile.generalinfo.edit.ui.ProfileGeneralInfoEditView
import com.aya.digital.core.model.ProfileSex
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber
import java.time.LocalDate

class ProfileGeneralInfoEditViewModel(
    private val param: ProfileGeneralInfoEditView.Param,
    private val coordinatorRouter: CoordinatorRouter,
    private val profileInfoUseCase: GetProfileInfoUseCase,
    private val saveProfileInfoUseCase: SaveProfileInfoUseCase,
    private val setAvatarUseCase: SetAvatarUseCase
) :
    BaseViewModel<ProfileGeneralInfoEditState, ProfileGeneralInfoEditSideEffect>() {
    override val container =
        container<ProfileGeneralInfoEditState, ProfileGeneralInfoEditSideEffect>(
            initialState = ProfileGeneralInfoEditState(),
        )
        {

        }

    init {
        param.profileModel?.let { initProfile(it) } ?: loadProfile()
    }

    private fun initProfile(profileInfo: ProfileInfoModel) = intent {
        setProfileInfoFields(profileInfo)
    }

    private fun loadProfile() = intent {
        val resultModel = profileInfoUseCase().await()
        resultModel.processResult({ profileInfo ->
            setProfileInfoFields(profileInfo)
        }, { processError(it) })
    }


    fun dropDownSelected(tag: Int, selectedItemTag: String) = intent {
        when (tag) {
            FieldsTags.SEX_FIELD_TAG -> {
                val selectedSex = ProfileSex.getSexByTag(selectedItemTag)
                reduce { state.copy(sex = selectedSex) }
            }
        }
    }

    fun selectFieldClicked(tag: Int) = intent {
        when (tag) {
            FieldsTags.BIRTH_DATE_FIELD_TAG -> {
                postSideEffect(ProfileGeneralInfoEditSideEffect.ShowBirthdayDatePicker(state.dateOfBirth))
            }
        }
    }

    fun nameFieldChanged(tag: Int, text: String) = intent {
        when (tag) {
            FieldsTags.FIRST_NAME_FIELD_TAG -> {
                reduce {
                    state.copy(firstName = text)
                }
            }
            FieldsTags.LAST_NAME_FIELD_TAG -> {
                reduce { state.copy(lastName = text) }
            }
            FieldsTags.MIDDLE_NAME_FIELD_TAG -> {
                reduce { state.copy(middleName = text) }
            }
        }
    }

    fun numberFieldChanged(tag: Int, text: String) = intent {
        when (tag) {
            FieldsTags.WEIGHT_FIELD_TAG -> {
                reduce { state.copy(weight = text) }
            }
            FieldsTags.HEIGHT_FIELD_TAG -> {
                reduce { state.copy(height = text) }
            }
            FieldsTags.SSN_OR_TIN_FIELD_TAG -> {
                reduce { state.copy(ssnOrTin = text) }
            }
        }
    }

    fun onSaveProfileClicked() = intent {
        val profileEditModel = getProfileEditModel(state)
                val await = saveProfileInfoUseCase(profileEditModel).await()
                await.processResult({if(it) loadProfile()}, { processError(it) })
    }

    fun birthDaySelected(date: LocalDate) = intent {
        reduce { state.copy(dateOfBirth = date) }
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
            ssn = state.ssnOrTin
            tin = state.ssnOrTin
        }

    fun avatarSelectClicked() = intent {
        postSideEffect(ProfileGeneralInfoEditSideEffect.SelectAvatar)
    }
    fun profileAvatarImageSelected(uri: Uri) = intent(registerIdling = false) {
        setAvatarUseCase(uri).await()
            .processResult({loadProfile()},{processError(it)})
    }

    private inline fun <reified T> compareFields(fieldFirst: T?, fieldSecond: T?): Boolean? =
        fieldFirst?.run {
            if (fieldSecond == null) return@run false
            return@run fieldSecond == fieldFirst
        }


    private fun setProfileInfoFields(profileInfo: ProfileInfoModel) = intent {
        reduce {
            state.copy(
                firstName = profileInfo.firstName,
                middleName = profileInfo.middleName,
                lastName = profileInfo.lastName,
                shortAddress = profileInfo.shortAddress,
                dateOfBirth = profileInfo.dateOfBirth,
                height = profileInfo.height,
                sex = profileInfo.sex,
                weight = profileInfo.weight,
                ssnOrTin = profileInfo.ssn ?: profileInfo.tin,
                avatarUrl = profileInfo.avatar?:""
            )

        }
    }

    private fun ProfileEditModel.shouldSaveProfile(state: ProfileGeneralInfoEditState): Boolean? {
        return true
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(ProfileGeneralInfoEditSideEffect.Error(errorSideEffect))
    }
}






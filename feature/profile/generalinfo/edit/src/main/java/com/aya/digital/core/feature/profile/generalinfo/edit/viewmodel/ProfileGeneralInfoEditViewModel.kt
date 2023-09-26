package com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel

import com.aya.digital.core.data.base.result.models.dictionaries.MultiSelectResultModel
import com.aya.digital.core.data.base.result.models.profile.ProfileSaveResult
import com.aya.digital.core.domain.profile.generalinfo.edit.SaveProfileInfoUseCase
import com.aya.digital.core.domain.profile.generalinfo.edit.SetAvatarUseCase
import com.aya.digital.core.domain.profile.generalinfo.edit.model.FlavoredProfileEditModel
import com.aya.digital.core.domain.profile.generalinfo.edit.model.ProfileEditModel
import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileInfoUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.model.FlavoredProfileModel
import com.aya.digital.core.domain.profile.generalinfo.view.model.ProfileInfoModel
import com.aya.digital.core.feature.profile.generalinfo.edit.FieldsTags
import com.aya.digital.core.feature.profile.generalinfo.edit.getRequestCodeForSelectionField
import com.aya.digital.core.feature.profile.generalinfo.edit.navigation.ProfileGeneralInfoEditNavigationEvents
import com.aya.digital.core.feature.profile.generalinfo.edit.ui.ProfileGeneralInfoEditView
import com.aya.digital.core.model.ProfileSex
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.AppFlavour
import com.aya.digital.core.navigation.Flavor
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate

class ProfileGeneralInfoEditViewModel(
    private val param: ProfileGeneralInfoEditView.Param,
    private val coordinatorRouter: CoordinatorRouter,
    private val rootCoordinatorRouter: CoordinatorRouter,
    private val profileInfoUseCase: GetProfileInfoUseCase,
    private val saveProfileInfoUseCase: SaveProfileInfoUseCase,
    private val flavour: AppFlavour,
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
                reduce {
                    state.copy(patientFields = state.patientFields?.apply {
                        sex = selectedSex
                    })
                }
            }
        }
    }

    fun selectFieldClicked(tag: Int) = intent {
        when (tag) {
            FieldsTags.BIRTH_DATE_FIELD_TAG -> {
                postSideEffect(ProfileGeneralInfoEditSideEffect.ShowBirthdayDatePicker(state.dateOfBirth))
            }

            FieldsTags.LANGUAGES_FIELD_TAG -> {
                selectField(tag,state.doctorFields?.languages?.map { it.id }?: listOf())
            }
            FieldsTags.MEDICAL_DEGREES_FIELD_TAG -> {
                selectField(tag,state.doctorFields?.medicalDegrees?.map { it.id }?: listOf())
            }
            FieldsTags.SPECIALISATIONS_FIELD_TAG -> {
                selectField(tag,state.doctorFields?.specialities?.map { it.id }?: listOf())
            }
        }
    }

    private fun selectField(fieldTag:Int,ids:List<Int>) = intent {
        val requestCode = fieldTag.getRequestCodeForSelectionField()
        val event = when(fieldTag)
        {
            FieldsTags.MEDICAL_DEGREES_FIELD_TAG -> ProfileGeneralInfoEditNavigationEvents.FieldSelection.SelectDegrees(requestCode,ids)
            FieldsTags.SPECIALISATIONS_FIELD_TAG ->ProfileGeneralInfoEditNavigationEvents.FieldSelection.SelectSpecialities(requestCode,ids)
            FieldsTags.LANGUAGES_FIELD_TAG ->ProfileGeneralInfoEditNavigationEvents.FieldSelection.SelectLanguages(requestCode,ids)
            else -> ProfileGeneralInfoEditNavigationEvents.FieldSelection.StubField
        }
        listenForFieldSelection(event)
        coordinatorRouter.sendEvent(event)
    /*    coordinatorRouter.sendEvent(
            ProfileGeneralInfoEditNavigationEvents.SelectLanguages(
                RequestCodes.LANGUAGES_LIST_REQUEST_CODE,
                state.doctorFields?.languages?.map { it.id } ?: listOf()
            )
        )*/
    }

    private fun listenForFieldSelection(event: ProfileGeneralInfoEditNavigationEvents.FieldSelection) {
        rootCoordinatorRouter.setResultListener(event.requestCode) { result ->
            if (result is MultiSelectResultModel && result.selectedItems.isNotEmpty()) {
                when(event)
                {
                    is ProfileGeneralInfoEditNavigationEvents.FieldSelection.SelectDegrees -> setMedicalDegrees(result.selectedItems.map { FlavoredProfileModel.DoctorProfileModel.Degree(id = it.id,code="",name=it.text) })
                    is ProfileGeneralInfoEditNavigationEvents.FieldSelection.SelectLanguages -> setLanguages(result.selectedItems.map { FlavoredProfileModel.DoctorProfileModel.Language(id = it.id,code="",name=it.text) })
                    is ProfileGeneralInfoEditNavigationEvents.FieldSelection.SelectSpecialities ->setSpecialities(result.selectedItems.map { FlavoredProfileModel.DoctorProfileModel.Speciality(id = it.id,code="",name=it.text) })
                    else -> {}
                }
            }
        }
    }

    private fun setLanguages(selectedLanguages: List<FlavoredProfileModel.DoctorProfileModel.Language>) = intent {
       reduce {
           state.copy(doctorFields = state.doctorFields?.apply { languages = selectedLanguages })
       }
    }

    private fun setMedicalDegrees(selectedDegrees: List<FlavoredProfileModel.DoctorProfileModel.Degree>) = intent {
        reduce {
            state.copy(doctorFields = state.doctorFields?.apply { medicalDegrees = selectedDegrees })
        }
    }

    private fun setSpecialities(selectedSpecialities: List<FlavoredProfileModel.DoctorProfileModel.Speciality>) = intent {
        reduce {
            state.copy(doctorFields = state.doctorFields?.apply { specialities = selectedSpecialities })
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

            FieldsTags.BIO_FIELD_TAG -> {
                reduce { state.copy(doctorFields = state.doctorFields?.apply { bio = text}) }
            }
        }
    }

    fun numberFieldChanged(tag: Int, text: String) = intent {
        when (tag) {
            FieldsTags.WEIGHT_FIELD_TAG -> {
                reduce { state.copy(patientFields = state.patientFields?.apply { weight = text }) }
            }

            FieldsTags.HEIGHT_FIELD_TAG -> {
                reduce { state.copy(patientFields = state.patientFields?.apply { height = text }) }
            }

            FieldsTags.SSN_OR_TIN_FIELD_TAG -> {
                reduce {
                    state.copy(patientFields = state.patientFields?.apply {
                        ssnOrTin = text
                    })
                }
            }
        }
    }

    fun onSaveProfileClicked() = intent {
        val profileEditModel = getProfileEditModel(state)
        val await = saveProfileInfoUseCase(profileEditModel).await()
        await.processResult({
           coordinatorRouter.sendEvent(
                ProfileGeneralInfoEditNavigationEvents.FinishWithResult(
                    param.requestCode,
                    ProfileSaveResult(true)
                )
            )
        }, { processError(it) })
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
            flavoredProfileEditModel = getProfileEditFlavoredFields(state)
        }

    private fun getProfileEditFlavoredFields(state: ProfileGeneralInfoEditState) =
        when (flavour.flavour) {
            Flavor.Doctor -> FlavoredProfileEditModel.DoctorProfileEditModel(
                bio = state.doctorFields?.bio,
                languages = state.doctorFields?.languages?.map {
                    FlavoredProfileEditModel.DoctorProfileEditModel.Language(
                        id = it.id,
                        code = it.code,
                        name = it.name
                    )
                },
                medicalDegrees = state.doctorFields?.medicalDegrees?.map {
                    FlavoredProfileEditModel.DoctorProfileEditModel.MedicalDegree(
                        id = it.id,
                        code = it.code,
                        name = it.name
                    )
                },
                specialities = state.doctorFields?.specialities?.map {
                    FlavoredProfileEditModel.DoctorProfileEditModel.Speciality(
                        id = it.id,
                        code = it.code,
                        name = it.name
                    )
                },
                )

            Flavor.Patient -> FlavoredProfileEditModel.PatientProfileEditModel(
                sex = state.patientFields?.sex,
                height = state.patientFields?.height,
                weight = state.patientFields?.weight,
                shortAddress = state.patientFields?.shortAddress,
                ssn = state.patientFields?.ssnOrTin,
                tin = state.patientFields?.ssnOrTin
            )
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
                dateOfBirth = profileInfo.dateOfBirth,
                avatarUrl = profileInfo.avatar ?: ""
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

            is FlavoredProfileModel.PatientProfileModel -> reduce {
                state.copy(
                    patientFields = PatientFields(
                        sex = flavoredProfileModel.sex,
                        height = flavoredProfileModel.height,
                        weight = flavoredProfileModel.weight,
                        shortAddress = flavoredProfileModel.shortAddress,
                        ssnOrTin = flavoredProfileModel.ssn ?: flavoredProfileModel.tin
                    )
                )
            }

            null -> {}
        }
    }

    private fun ProfileEditModel.shouldSaveProfile(state: ProfileGeneralInfoEditState): Boolean? {
        return true
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(ProfileGeneralInfoEditSideEffect.Error(errorSideEffect))
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }
}






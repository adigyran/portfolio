package com.aya.digital.core.feature.profile.generalinfo.edit.ui.model

import android.content.Context
import android.text.InputType
import com.aya.digital.core.baseresources.R.*
import com.aya.digital.core.feature.profile.generalinfo.edit.FieldsTags
import com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel.ProfileGeneralInfoEditState
import com.aya.digital.core.model.ProfileSex
import com.aya.digital.core.model.getSexName
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.navigation.AppFlavour
import com.aya.digital.core.navigation.Flavor
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.base.masks.CommonMasks
import com.aya.digital.core.ui.delegates.components.fields.dropdown.model.AutoCompleteItem
import com.aya.digital.core.ui.delegates.components.fields.name.model.NameFieldUIModel
import com.aya.digital.core.ui.delegates.components.fields.dropdown.model.DropdownFieldUIModel
import com.aya.digital.core.ui.delegates.components.fields.selection.model.SelectionFieldUIModel
import com.aya.digital.core.ui.delegates.components.fields.validated.model.ValidatedFieldUIModel
import com.aya.digital.core.util.datetime.DateTimeUtils
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toKotlinLocalDate

class ProfileGeneralInfoEditStateTransformer(
    private val context: Context,
    private val dateTimeUtils: DateTimeUtils,
    private val appFlavour: AppFlavour
) :
    BaseStateTransformer<ProfileGeneralInfoEditState, ProfileGeneralInfoEditUiModel>() {
    override fun invoke(state: ProfileGeneralInfoEditState): ProfileGeneralInfoEditUiModel =
        ProfileGeneralInfoEditUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    add(
                        NameFieldUIModel(
                            FieldsTags.FIRST_NAME_FIELD_TAG,
                            "First Name",
                            text = state.firstName.getField(),
                            null
                        )
                    )
                    add(
                        NameFieldUIModel(
                            FieldsTags.LAST_NAME_FIELD_TAG,
                            "Last Name",
                            text = state.lastName.getField(),
                            null
                        )
                    )
                    add(
                        NameFieldUIModel(
                            FieldsTags.MIDDLE_NAME_FIELD_TAG,
                            "Middle Name",
                            state.middleName.getField(),
                            null
                        )
                    )

                    add(
                        SelectionFieldUIModel(
                            FieldsTags.BIRTH_DATE_FIELD_TAG,
                            "Date of Birth",
                            state.dateOfBirth?.toKotlinLocalDate().getField(),
                            null,
                            getBirthDayIcon()
                        )
                    )
                    addAll(appFlavour.flavour.getAppSpecificFields(state))

                }
            },
            avatarUrl = state.avatarUrl
        )

    private fun Flavor.getAppSpecificFields(state: ProfileGeneralInfoEditState) = when(this)
    {
        Flavor.Doctor -> getDoctorFields(state)
        Flavor.Patient -> getPatientFields(state)
    }

    private fun getPatientFields(state: ProfileGeneralInfoEditState) = mutableListOf<DiffItem>().apply {
        add(
            ValidatedFieldUIModel(
                tag = FieldsTags.SSN_OR_TIN_FIELD_TAG,
                label = "SSN or TIN",
                text = state.patientFields?.ssnOrTin.getField(),
                error = state.patientFields?.ssnOrTinError,
                mask = CommonMasks.getSSNValidator(),
                suffix = null,
                inputType = InputType.TYPE_CLASS_NUMBER
            )
        )
        add(
            DropdownFieldUIModel(
                FieldsTags.SEX_FIELD_TAG,
                getSexesList(),
                "Sex",
                state.patientFields?.sex.getField(),
                null
            )
        )
        add(
            ValidatedFieldUIModel(
                FieldsTags.HEIGHT_FIELD_TAG,
                "Height",
                state.patientFields?.height.getField(),
                state.patientFields?.heightError,
                suffix = getHeightUnit(),
                inputType = InputType.TYPE_CLASS_NUMBER,
                mask = CommonMasks.getHeightValidator()
            )
        )
        add(
            ValidatedFieldUIModel(
                FieldsTags.WEIGHT_FIELD_TAG,
                "Weight",
                state.patientFields?.weight.getField(),
                state.patientFields?.weightError,
                suffix = getWeightUnit(),
                inputType = InputType.TYPE_CLASS_NUMBER,
                mask = CommonMasks.getWeightValidator()
            )
        )
        add(
            NameFieldUIModel(
                FieldsTags.SHORT_ADDRESS_FIELD_TAG,
                "Short Address",
                state.patientFields?.shortAddress.getField(),
                null,
            )
        )
    }

    private fun getDoctorFields(state: ProfileGeneralInfoEditState) = mutableListOf<DiffItem>().apply {
/*        add(ValidatedFieldUIModel(
            tag = FieldsTags.NPI_FIELD_TAG,
            label = "NPI",
            text = state.doctorFields?.npi.getField(),
            error = state.doctorFields?.npiError,
            mask = CommonMasks.getSSNValidator(),
            suffix = null,
            inputType = InputType.TYPE_CLASS_NUMBER
        ))
        add(ValidatedFieldUIModel(
            tag = FieldsTags.TIN_FIELD_TAG,
            label = "TIN",
            text = state.doctorFields?.tin.getField(),
            error = state.doctorFields?.tinError,
            mask = CommonMasks.getSSNValidator(),
            suffix = null,
            inputType = InputType.TYPE_CLASS_NUMBER
        ))
        add(ValidatedFieldUIModel(
            tag = FieldsTags.LICENSE_NUMBER_FIELD_TAG,
            label = "License number",
            text = state.doctorFields?.licenseNumber.getField(),
            error = state.doctorFields?.licenseNumberError,
            mask = CommonMasks.getSSNValidator(),
            suffix = null,
            inputType = InputType.TYPE_CLASS_NUMBER
        ))*/
        add(
            SelectionFieldUIModel(
                FieldsTags.LANGUAGES_FIELD_TAG,
                "Language",
               "",
                state.doctorFields?.languagesError
            )
        )

        add(
            NameFieldUIModel(
                FieldsTags.BIO_FIELD_TAG,
                "Bio",
                state.doctorFields?.bio.getField(),
                state.doctorFields?.bioError
            )
        )

    }

    private fun getSexesList() = ProfileSex.getAllSexes()
        .map { profileSex -> AutoCompleteItem(profileSex.tag, profileSex.sexName()) }

    private fun ProfileSex.sexName() = this.getSexName(context)

    private fun String?.getField() = this ?: ""
    private fun ProfileSex?.getField() = this?.sexName() ?: ""
    private fun LocalDate?.getField() = this?.let { dateTimeUtils.formatBirthDate(it) } ?: ""
    private fun getHeightUnit() = "ft."
    private fun getWeightUnit() = "lbs"

    private fun getBirthDayIcon() = drawable.ic_calendar_icon


}
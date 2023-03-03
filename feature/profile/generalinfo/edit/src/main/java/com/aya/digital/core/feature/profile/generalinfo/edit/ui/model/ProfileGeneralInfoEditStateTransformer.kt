package com.aya.digital.core.feature.profile.generalinfo.edit.ui.model

import android.content.Context
import com.aya.digital.core.baseresources.R.*
import com.aya.digital.core.feature.profile.generalinfo.edit.FieldsTags
import com.aya.digital.core.feature.profile.generalinfo.edit.R
import com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel.ProfileGeneralInfoEditState
import com.aya.digital.core.model.ProfileSex
import com.aya.digital.core.model.getSexName
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.name.model.NameFieldUIModel
import com.aya.digital.core.ui.delegates.components.fields.selection.model.DropdownFieldUIModel
import com.aya.digital.core.ui.delegates.components.fields.selection.model.SelectionFieldUIModel
import java.time.LocalDate

class ProfileGeneralInfoEditStateTransformer(private val context: Context) :
    BaseStateTransformer<ProfileGeneralInfoEditState, ProfileGeneralInfoEditUiModel>() {
    override fun invoke(state: ProfileGeneralInfoEditState): ProfileGeneralInfoEditUiModel =
        ProfileGeneralInfoEditUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    add(
                        NameFieldUIModel(
                            FieldsTags.FIRST_NAME_FIELD_TAG,
                            "First Name",
                            state.firstName.getField(),
                            null
                        )
                    )
                    add(
                        NameFieldUIModel(
                            FieldsTags.LAST_NAME_FIELD_TAG,
                            "Last Name",
                            state.lastName.getField(),
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
                            state.birthDate.getField(),
                            null,
                            getBirthDayIcon()
                        )
                    )
                    add(
                        DropdownFieldUIModel(
                            FieldsTags.SEX_FIELD_TAG,
                            getSexesList(),
                            "Sex",
                            "",
                            null
                        )
                    )
                    add(
                        NameFieldUIModel(
                            FieldsTags.HEIGHT_FIELD_TAG,
                            "Height",
                            state.height.getField(),
                            null,
                            suffix = getHeightUnit()
                        )
                    )
                    add(
                        NameFieldUIModel(
                            FieldsTags.WEIGHT_FIELD_TAG,
                            "Weight",
                            state.height.getField(),
                            null,
                            suffix =  getWeightUnit()
                        )
                    )
                    add(
                        NameFieldUIModel(
                            FieldsTags.SHORT_ADDRESS_FIELD_TAG,
                            "Short Address",
                            state.shortAddress.getField(),
                            null,
                        )
                    )
                }
            }
        )


    private fun getSexesList() = ProfileSex.getAllSexes().map { it.sexName() }
    private fun ProfileSex.sexName() = this.getSexName(context)

    private fun String?.getField() = this ?: ""
    private fun ProfileSex?.getField() = this?.sexName() ?: ""
    private fun LocalDate?.getField() = this?.let { "TODO" } ?: ""
    private fun getHeightUnit() = "ft."
    private fun getWeightUnit() = "lbs"

    private fun getBirthDayIcon() = drawable.ic_birthday_field_icon


}
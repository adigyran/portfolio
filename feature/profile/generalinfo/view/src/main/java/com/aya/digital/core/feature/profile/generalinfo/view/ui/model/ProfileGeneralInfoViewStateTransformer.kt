package com.aya.digital.core.feature.profile.generalinfo.view.ui.model

import android.content.Context
import com.aya.digital.core.feature.profile.generalinfo.view.viewmodel.ProfileGeneralInfoViewState
import com.aya.digital.core.model.ProfileSex
import com.aya.digital.core.model.getSexName
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.profile.info.model.ProfileInfoUIModel
import com.aya.digital.core.util.datetime.DateTimeUtils
import kotlinx.datetime.LocalDate

class ProfileGeneralInfoViewStateTransformer(
    private val context: Context,
    private val dateTimeUtils: DateTimeUtils
) :
    BaseStateTransformer<ProfileGeneralInfoViewState, ProfileGeneralInfoViewUiModel>() {
    override fun invoke(state: ProfileGeneralInfoViewState): ProfileGeneralInfoViewUiModel =
        ProfileGeneralInfoViewUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    add(ProfileInfoUIModel("Email", state.email.getField()))
                    add(ProfileInfoUIModel("First Name", state.firstName.getField()))
                    add(ProfileInfoUIModel("Last Name", state.lastName.getField()))
                    add(ProfileInfoUIModel("Middle Name", state.middleName.getField()))
                    add(ProfileInfoUIModel("SSN", state.ssn.getField()))
                    add(ProfileInfoUIModel("TIN", state.tin.getField()))
                    add(ProfileInfoUIModel("Date of Birth", state.dateOfBirth.getField()))
                    add(ProfileInfoUIModel("Sex", state.sex.getField()))
                    add(ProfileInfoUIModel("Height", state.height.getUnitField(getHeightUnit())))
                    add(ProfileInfoUIModel("Weight", state.weight.getUnitField(getWeightUnit())))
                    add(ProfileInfoUIModel("Short Address", state.shortAddress.getField()))

                }
            },
            avatar = state.avatar
        )

    private fun getNotSpecifiedText() = "Not specified"
    private fun String?.getField() = this ?: getNotSpecifiedText()
    private fun ProfileSex?.getField() = this?.sexName() ?: getNotSpecifiedText()
    private fun LocalDate?.getField() =
        this?.let { dateTimeUtils.formatBirthDate(it) } ?: getNotSpecifiedText()

    private fun ProfileSex.sexName() = this.getSexName(context)

    private fun String?.getUnitField(unit: String) =
        this?.let { "%s %s".format(it, unit) } ?: getNotSpecifiedText()

    private fun getHeightUnit() = "ft."
    private fun getWeightUnit() = "lbs"
}


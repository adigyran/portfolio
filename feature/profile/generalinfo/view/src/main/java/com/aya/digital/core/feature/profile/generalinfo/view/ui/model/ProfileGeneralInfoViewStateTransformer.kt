package com.aya.digital.core.feature.profile.generalinfo.view.ui.model

import android.content.Context
import com.aya.digital.core.feature.profile.generalinfo.view.FieldsTags
import com.aya.digital.core.feature.profile.generalinfo.view.viewmodel.ProfileGeneralInfoViewState
import com.aya.digital.core.model.ProfileSex
import com.aya.digital.core.model.getSexName
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.base.ext.SpannableObject
import com.aya.digital.core.ui.delegates.profile.info.model.ProfileInfoUIModel
import timber.log.Timber
import java.time.LocalDate

class ProfileGeneralInfoViewStateTransformer(private val context: Context) :
    BaseStateTransformer<ProfileGeneralInfoViewState, ProfileGeneralInfoViewUiModel>() {
    override fun invoke(state: ProfileGeneralInfoViewState): ProfileGeneralInfoViewUiModel =
        ProfileGeneralInfoViewUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    add(ProfileInfoUIModel("First Name", state.firstName.getField()))
                    add(ProfileInfoUIModel("Last Name", state.lastName.getField()))
                    add(ProfileInfoUIModel("Middle Name", state.middleName.getField()))
                    add(ProfileInfoUIModel("Date of Birth", state.dateOfBirth.getField()))
                    add(ProfileInfoUIModel("Sex", state.sex.getField()))
                    add(ProfileInfoUIModel("Height", state.height.getField()))
                    add(ProfileInfoUIModel("Weight", state.weight.getField()))
                    add(ProfileInfoUIModel("Short Address", state.shortAddress.getField()))

                }
            },
            avatar = state.avatar
        )

    private fun getNotSpecifiedText() = "Not specified"
    private fun String?.getField() = this ?: getNotSpecifiedText()
    private fun ProfileSex?.getField() = this?.sexName() ?: getNotSpecifiedText()
    private fun LocalDate?.getField() = this?.let { "TODO" } ?: getNotSpecifiedText()
    private fun ProfileSex.sexName() = this.getSexName(context)
}


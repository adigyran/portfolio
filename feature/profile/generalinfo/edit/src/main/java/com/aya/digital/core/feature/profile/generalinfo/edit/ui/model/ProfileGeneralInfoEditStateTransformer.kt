package com.aya.digital.core.feature.profile.generalinfo.edit.ui.model

import android.content.Context
import com.aya.digital.core.feature.profile.generalinfo.edit.FieldsTags
import com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel.ProfileGeneralInfoEditState
import com.aya.digital.core.model.ProfileSex
import com.aya.digital.core.model.getSexName
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.selection.model.DropdownFieldUIModel

class ProfileGeneralInfoEditStateTransformer(private val context : Context): BaseStateTransformer<ProfileGeneralInfoEditState, ProfileGeneralInfoEditUiModel>() {
    override fun invoke(state: ProfileGeneralInfoEditState): ProfileGeneralInfoEditUiModel =
        ProfileGeneralInfoEditUiModel(
            data = kotlin.run {
               return@run mutableListOf<DiffItem>().apply {
                   add(DropdownFieldUIModel(FieldsTags.SEX_FIELD_TAG,getSexesList(),"Sex","",null))
                }
            }
        )


    fun getSexesList() = ProfileSex.getAllSexes().map { it.sexName() }
    private fun ProfileSex.sexName() = this.getSexName(context)

}
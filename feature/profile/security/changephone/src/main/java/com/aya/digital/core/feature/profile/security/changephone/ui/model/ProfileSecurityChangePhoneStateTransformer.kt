package com.aya.digital.core.feature.profile.security.changephone.ui.model

import android.content.Context
import com.aya.digital.core.feature.profile.security.changephone.FieldsTags
import com.aya.digital.core.feature.profile.security.changephone.viewmodel.ProfileSecurityChangePhoneState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.base.masks.CommonMasks
import com.aya.digital.core.ui.delegates.components.fields.emailphone.model.PhoneFieldUIModel
import com.aya.digital.core.ui.delegates.components.labels.headline.model.HeadlineTwoLineLabelUIModel

internal class ProfileSecurityChangePhoneStateTransformer(context: Context) :
    BaseStateTransformer<ProfileSecurityChangePhoneState, ProfileSecurityChangePhoneUiModel>() {
    override fun invoke(state: ProfileSecurityChangePhoneState): ProfileSecurityChangePhoneUiModel =
        ProfileSecurityChangePhoneUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    add(
                        HeadlineTwoLineLabelUIModel(
                            "Change phone",
                            "You can use your phone to log in"
                        )
                    )
                    add(
                        PhoneFieldUIModel(
                            FieldsTags.EMAIL_PHONE_FIELD_TAG,
                            "Phone",
                            state.phone,
                            state.phoneError,
                            CommonMasks.getUsPhoneValidator()
                        )
                    )
                }
            }
        )


}
package com.aya.digital.core.feature.profile.security.securitysummary.ui.model

import android.content.Context
import com.aya.digital.core.feature.profile.security.securitysummary.FieldsTags
import com.aya.digital.core.feature.profile.security.securitysummary.viewmodel.ProfileSecuritySummaryState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model.SecuritySummaryUIModel

internal class ProfileSecuritySummaryStateTransformer(context: Context) :
    BaseStateTransformer<ProfileSecuritySummaryState, ProfileSecuritySummaryUiModel>() {
    override fun invoke(state: ProfileSecuritySummaryState): ProfileSecuritySummaryUiModel =
        ProfileSecuritySummaryUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    add(
                        SecuritySummaryUIModel(
                            FieldsTags.EMAIL_FIELD_TAG,
                            "Email",
                            state.email.getField()
                        )
                    )
                    add(
                        SecuritySummaryUIModel(
                            FieldsTags.PASSWORD_FIELD_TAG,
                            "Password",
                            state.password.getPasswordField()
                        )
                    )
                }
            }
        )

    private fun String?.getField() = this ?: "Not added"
    private fun String?.getPasswordField() =
        this?.replace(Regex(".*"),"*") ?: "Not added"

}
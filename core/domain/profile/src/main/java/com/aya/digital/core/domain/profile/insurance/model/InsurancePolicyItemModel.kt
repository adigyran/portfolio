package com.aya.digital.core.domain.profile.insurance.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InsurancePolicyItemModel(
    val id: Int,
    val organisationId: Int,
    val organisationName:String,
    val number: String,
    val attachmentUrl:String
) : Parcelable
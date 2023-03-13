package com.aya.digital.core.domain.profile.insurance.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InsurancePolicyItemModel(
    val id: Int,
    val name: Int,
    val number: String,
    val photo: Int
) : Parcelable
package com.aya.digital.core.domain.profile.address.model

import android.os.Parcelable
import android.text.SpannableString
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class PlacePredictionModel(
    val id: String,
    val primaryText: String,
    val secondaryText: String,
    val formattedString:String
) : Parcelable

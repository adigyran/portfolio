package com.aya.digital.core.domain.home.lastupdates.model

import android.os.Parcelable
import com.aya.digital.core.domain.home.lastupdates.model.LastUpdatesItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class LastUpdatesModel(val title: String, val items: List<LastUpdatesItem>) : Parcelable


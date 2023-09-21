package com.aya.digital.core.feature.choosers.selectwithsearch.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SelectionItem(val id: Int, val text: String) : Parcelable
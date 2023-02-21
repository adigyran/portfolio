package com.aya.digital.core.ui.adapters.base

import android.os.Parcelable

interface DiffItem {
    fun areItemsTheSame(newItem: DiffItem): Boolean

    fun areContentsTheSame(newItem: DiffItem): Boolean
}

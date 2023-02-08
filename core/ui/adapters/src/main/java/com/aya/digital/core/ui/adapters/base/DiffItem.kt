package com.aya.digital.core.ui.adapters.base

interface DiffItem {
    fun areItemsTheSame(newItem: DiffItem): Boolean

    fun areContentsTheSame(newItem: DiffItem): Boolean
}

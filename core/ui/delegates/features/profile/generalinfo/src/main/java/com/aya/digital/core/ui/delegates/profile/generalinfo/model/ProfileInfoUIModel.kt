package com.aya.digital.core.ui.delegates.profile.info.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class ProfileInfoUIModel(val label: String, val value: String) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is ProfileInfoUIModel && newItem.label == this.label

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is ProfileInfoUIModel && newItem.label == this.label && newItem.value == this.value
}
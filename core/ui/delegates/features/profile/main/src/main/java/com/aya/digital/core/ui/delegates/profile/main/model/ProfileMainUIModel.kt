package com.aya.digital.core.ui.delegates.profile.info.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class ProfileMainUIModel(val icon: Int, val value: String) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is ProfileMainUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is ProfileMainUIModel && newItem.icon == this.icon && newItem.value == this.value
}
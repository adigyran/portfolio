package com.aya.digital.core.ui.delegates.profile.info.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class ProfileMainUIModel(val tag:Int,val icon: Int, val value: String) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is ProfileMainUIModel && newItem.tag == this.tag

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is ProfileMainUIModel && newItem.tag == this.tag && newItem.icon == this.icon && newItem.value == this.value
}
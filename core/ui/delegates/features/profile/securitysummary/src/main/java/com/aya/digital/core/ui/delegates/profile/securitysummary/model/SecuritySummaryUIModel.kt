package com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class SecuritySummaryUIModel(val tag:Int,val label: String, val value: String) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is SecuritySummaryUIModel && newItem.tag == this.tag && newItem.label == this.label

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is SecuritySummaryUIModel && newItem.tag == this.tag && newItem.label == this.label && newItem.value == this.value
}
package com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class InsurancePolicyUIModel(val label: String, val value: String) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is InsurancePolicyUIModel && newItem.label == this.label

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is InsurancePolicyUIModel && newItem.label == this.label && newItem.value == this.value
}
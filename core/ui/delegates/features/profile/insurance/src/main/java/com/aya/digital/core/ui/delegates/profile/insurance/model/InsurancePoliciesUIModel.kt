package com.aya.digital.core.ui.delegates.profile.insurance.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class InsurancePoliciesUIModel(
    val id: Int,
    val count: String,
) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is InsurancePoliciesUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is InsurancePoliciesUIModel
                && newItem.id == this.id
                && newItem.count == this.count

}
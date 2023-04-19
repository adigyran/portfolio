package com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model

import com.aya.digital.core.model.InsuranceStatus
import com.aya.digital.core.ui.adapters.base.DiffItem

data class InsurancePolicyUIModel(
    val id: Int,
    val number: String,
    val name: String,
    val photo:String?,
    val status: InsuranceStatus?
) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is InsurancePolicyUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is InsurancePolicyUIModel
                && newItem.id == this.id
                && newItem.number == this.number
                && newItem.name == this.name
                && newItem.photo == this.photo
                && newItem.status == this.status
}
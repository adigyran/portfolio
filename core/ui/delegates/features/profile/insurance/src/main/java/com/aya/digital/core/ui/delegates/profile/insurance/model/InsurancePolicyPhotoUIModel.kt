package com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class InsurancePolicyPhotoUIModel(val photo:String?) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is InsurancePolicyPhotoUIModel
    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is InsurancePolicyPhotoUIModel && newItem.photo == this.photo
}
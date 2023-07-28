package com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class EmergencyContactInfoUIModel(val label: String, val value: String) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is EmergencyContactInfoUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is EmergencyContactInfoUIModel && newItem.value == this.value
}
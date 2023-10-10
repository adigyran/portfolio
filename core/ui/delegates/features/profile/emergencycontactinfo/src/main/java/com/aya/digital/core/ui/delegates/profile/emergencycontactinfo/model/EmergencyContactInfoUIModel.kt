package com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class EmergencyContactInfoUIModel(val id:Int, val fullName:String, val phone:String, val summary:String, val type:String) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is EmergencyContactInfoUIModel
                && newItem.id == this.id

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is EmergencyContactInfoUIModel && newItem.id == this.id
                && newItem.fullName == this.fullName
                && newItem.phone == this.phone
                && newItem.summary == this.summary
                && newItem.type == this.type

}
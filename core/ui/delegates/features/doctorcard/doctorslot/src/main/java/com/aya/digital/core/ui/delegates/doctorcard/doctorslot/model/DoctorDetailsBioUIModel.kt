package com.aya.digital.core.ui.delegates.doctorcard.doctorslot.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class DoctorDetailsBioUIModel(val id:Int, val bio:String, var isExpanded:Boolean = false) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is DoctorDetailsBioUIModel
    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is DoctorDetailsBioUIModel && newItem.id == this.id && newItem.bio == this.bio && newItem.isExpanded == this.isExpanded
}
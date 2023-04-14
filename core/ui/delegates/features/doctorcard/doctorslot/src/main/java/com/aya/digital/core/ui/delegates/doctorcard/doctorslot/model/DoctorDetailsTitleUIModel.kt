package com.aya.digital.core.ui.delegates.doctorcard.doctorslot.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class DoctorDetailsTitleUIModel(val id:Int, val titleText:String) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is DoctorDetailsTitleUIModel
    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is DoctorDetailsTitleUIModel && newItem.id == this.id && newItem.titleText == this.titleText
}
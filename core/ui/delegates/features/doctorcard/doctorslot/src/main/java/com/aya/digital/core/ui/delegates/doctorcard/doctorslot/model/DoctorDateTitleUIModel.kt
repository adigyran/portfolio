package com.aya.digital.core.ui.delegates.doctorcard.doctorslot.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class DoctorDateTitleUIModel(val id:Int, val dateText:String) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is DoctorDateTitleUIModel
    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is DoctorDateTitleUIModel && newItem.id == this.id && newItem.dateText == this.dateText
}
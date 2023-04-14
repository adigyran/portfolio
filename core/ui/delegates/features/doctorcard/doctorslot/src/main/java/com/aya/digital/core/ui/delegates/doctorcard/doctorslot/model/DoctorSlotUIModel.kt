package com.aya.digital.core.ui.delegates.doctorcard.doctorslot.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class DoctorSlotUIModel(val id:Int, val timeText:String) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is DoctorSlotUIModel
    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is DoctorSlotUIModel && newItem.id == this.id && newItem.timeText == this.timeText
}
package com.aya.digital.core.ui.delegates.doctorcard.doctorslot.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import kotlinx.datetime.LocalDate

data class DoctorSlotUIModel(
    val id: Int,
    val timeText: String,
    val date: LocalDate? = null,
    val selected: Boolean = false
) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is DoctorSlotUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is DoctorSlotUIModel
                && newItem.id == this.id
                && newItem.timeText == this.timeText
                && newItem.date == this.date
                && newItem.selected == this.selected
}
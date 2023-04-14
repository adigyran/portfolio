package com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class DoctorDetailsTitleUIModel(val titleText:String) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is DoctorDetailsTitleUIModel
    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is DoctorDetailsTitleUIModel && newItem.titleText == this.titleText
}
package com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class DoctorDetailsLocationUIModel(val address:String) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is DoctorDetailsLocationUIModel
    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is DoctorDetailsLocationUIModel && newItem.address == this.address
}
package com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class DoctorDetailsInsuranceUIModel(val insuranceName:String) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is DoctorDetailsInsuranceUIModel
    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is DoctorDetailsInsuranceUIModel && newItem.insuranceName == this.insuranceName
}
package com.aya.digital.core.ui.delegates.doctors.doctoritem.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class DoctorItemUIModel(
    val id: Int,
    val name: String,
    val speciality: String,
    val photo: String?,
    val isFavorite: Boolean = false
) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is DoctorItemUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is DoctorItemUIModel
                && newItem.id == this.id
                && newItem.name == this.name
                && newItem.speciality == this.speciality
                && newItem.photo == this.photo
                && newItem.isFavorite == this.isFavorite
}
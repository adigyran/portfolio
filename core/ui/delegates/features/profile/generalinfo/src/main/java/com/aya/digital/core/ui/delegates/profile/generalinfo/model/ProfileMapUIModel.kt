package com.aya.digital.core.ui.delegates.profile.generalinfo.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.google.android.gms.maps.model.LatLng

data class ProfileMapUIModel(val coordinates:LatLng) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is ProfileMapUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is ProfileMapUIModel && newItem.coordinates == this.coordinates
}
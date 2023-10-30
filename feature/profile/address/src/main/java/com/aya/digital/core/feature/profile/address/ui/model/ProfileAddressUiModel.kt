package com.aya.digital.core.feature.profile.address.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.google.android.gms.maps.model.LatLng

data class ProfileAddressUiModel(
    val selectedAddressLoc: LatLng?,
    val currentAddress: String? = null,
    val data: List<DiffItem>? = null
) : BaseUiModel

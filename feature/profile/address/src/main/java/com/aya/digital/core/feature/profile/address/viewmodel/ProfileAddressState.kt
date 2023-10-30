package com.aya.digital.core.feature.profile.address.viewmodel

import com.aya.digital.core.data.base.dataprocessing.dataloading.DataLoadingOperation
import com.aya.digital.core.domain.profile.address.model.PlacePredictionModel
import com.aya.digital.core.mvi.BaseState
import com.google.android.gms.maps.model.LatLng
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileAddressState(
   val dataLoadingOperation: DataLoadingOperation = DataLoadingOperation.Idle,
   val currentPredictions:List<PlacePredictionModel>?=null,
   val selectedLocation:LatLng?=null,
   val selectedAddress:String?=null
) : BaseState

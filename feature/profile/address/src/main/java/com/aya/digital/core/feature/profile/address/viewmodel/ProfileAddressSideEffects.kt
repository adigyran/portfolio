package com.aya.digital.core.feature.profile.address.viewmodel

import android.location.Location
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.google.android.gms.maps.model.LatLng

sealed class ProfileAddressSideEffects:BaseSideEffect {

    data class OnLocationChanged(val location:Location): ProfileAddressSideEffects()

    data class MoveMap(val latLng: LatLng): ProfileAddressSideEffects()
    data class Error(val error:BaseViewModel.ErrorSideEffect) : ProfileAddressSideEffects()
}

package com.aya.digital.core.domain.location.model

import android.location.Location

sealed class LocationResult {
    object CoordinatesNotAvailable : LocationResult()

    data class Success(val location: Location) : LocationResult()

    object LastLocationExceptionNotResolved : LocationResult()
    object CheckLocationSettingsExceptionNotResolved : LocationResult()
    data class UnresolvableException(val ex: Exception) : LocationResult()
}

sealed class Result {
    data class Success(val result: LocationResult) : Result()
    object ShouldGoToSettings : Result()
    class ShouldShowRationaleDialog() :
        Result()
}
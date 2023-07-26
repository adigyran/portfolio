package com.aya.digital.core.data.location

import android.location.Location

sealed class LocResult {
    object CoordinatesNotAvailable : LocResult()

    data class Success(val location: Location) : LocResult()

    object LastLocationExceptionNotResolved : LocResult()
    object CheckLocationSettingsExceptionNotResolved : LocResult()
    data class UnresolvableException(val ex: Exception) : LocResult()
}

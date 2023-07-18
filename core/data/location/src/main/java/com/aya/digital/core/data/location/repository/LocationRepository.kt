package com.aya.digital.core.data.location.repository

import android.location.Location
import io.reactivex.rxjava3.core.Observable

interface LocationRepository {
    sealed class LocResult {
        object CoordinatesNotAvailable : LocResult()

        data class Success(val location: Location) : LocResult()

        object LastLocationExceptionNotResolved : LocResult()
        object CheckLocationSettingsExceptionNotResolved : LocResult()
        data class UnresolvableException(val ex: Exception) : LocResult()
    }

    sealed class Result {
        data class Success(val result: LocResult) : Result()
        object ShouldGoToSettings : Result()
        data class ShouldShowRationaleDialog(val list: List<PermissionsRepository.Permission>) :
            Result()
    }

    fun getLocation(): Observable<Result>


}
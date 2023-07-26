package com.aya.digital.core.domain.location.model

import android.location.Location
import com.aya.digital.core.data.location.LocResult
import com.aya.digital.core.data.location.Result

sealed class LocationResultModel {
    object CoordinatesNotAvailable : LocationResultModel()

    data class Success(val location: Location) : LocationResultModel()

    object LastLocationExceptionNotResolved : LocationResultModel()
    object CheckLocationSettingsExceptionNotResolved : LocationResultModel()
    data class UnresolvableException(val ex: Exception) : LocationResultModel()
}

internal fun LocResult.toLocationResultModel() = when(this)
{
    LocResult.CheckLocationSettingsExceptionNotResolved -> LocationResultModel.CheckLocationSettingsExceptionNotResolved
    LocResult.CoordinatesNotAvailable -> LocationResultModel.CoordinatesNotAvailable
    LocResult.LastLocationExceptionNotResolved -> LocationResultModel.LastLocationExceptionNotResolved
    is LocResult.Success -> LocationResultModel.Success(location)
    is LocResult.UnresolvableException -> LocationResultModel.UnresolvableException(ex)
}

sealed class ResultModel {
    data class Success(val result: LocationResultModel) : ResultModel()
    object ShouldGoToSettings : ResultModel()
    class ShouldShowRationaleDialog() :
        ResultModel()
}

internal fun Result.toResultModel() = when(this)
{
    Result.ShouldGoToSettings -> ResultModel.ShouldGoToSettings
    is Result.ShouldShowRationaleDialog -> ResultModel.ShouldShowRationaleDialog()
    is Result.Success -> ResultModel.Success(result.toLocationResultModel())
}
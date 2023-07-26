package com.aya.digital.core.data.location

sealed class Result {
    data class Success(val result: LocResult) : Result()
    object ShouldGoToSettings : Result()
    class ShouldShowRationaleDialog() :
        Result()
}
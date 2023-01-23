package com.aya.digital.core.data.dataprocessing.dataloading

import com.aya.digital.core.networkbase.server.RequestResult

sealed class DataLoadingEffect<T> {
    class Loading<T> : DataLoadingEffect<T>()
    data class Loaded<T>(val payload: List<T>) :
        DataLoadingEffect<T>()

    data class LoadingError<T>(val throwable: RequestResult.Error) : DataLoadingEffect<T>()

    class Refreshing<T> : DataLoadingEffect<T>()
    data class Refreshed<T>(val payload: List<T>) :
        DataLoadingEffect<T>()

    data class RefreshingError<T>(val throwable: RequestResult.Error) :
        DataLoadingEffect<T>()

    val isLoading: Boolean
        get() = this is Loading
    val isLoaded: Boolean
        get() = this is Loaded
    val isLoadingError: Boolean
        get() = this is LoadingError

    val isRefreshing: Boolean
        get() = this is Refreshing
    val isRefreshed: Boolean
        get() = this is Refreshed
    val isRefreshingError: Boolean
        get() = this is RefreshingError
}
package com.aya.digital.core.data.dataprocessing.dataloading

import com.aya.digital.core.networkbase.server.RequestResult

sealed class DataLoadingEffectWithPagination<T> {
    class Loading<T> : DataLoadingEffectWithPagination<T>()
    data class Loaded<T>(val payload: List<T>, val isNextPageAvailable: Boolean) :
        DataLoadingEffectWithPagination<T>()

    data class LoadingError<T>(val throwable: RequestResult.Error) :
        DataLoadingEffectWithPagination<T>()

    class Refreshing<T> : DataLoadingEffectWithPagination<T>()
    data class Refreshed<T>(val payload: List<T>, val isNextPageAvailable: Boolean) :
        DataLoadingEffectWithPagination<T>()

    data class RefreshingError<T>(val throwable: RequestResult.Error) :
        DataLoadingEffectWithPagination<T>()

    class NextPageLoading<T> : DataLoadingEffectWithPagination<T>()
    data class NextPageLoaded<T>(val payload: List<T>, val isNextPageAvailable: Boolean) :
        DataLoadingEffectWithPagination<T>()

    data class NextPageLoadingError<T>(val throwable: RequestResult.Error) :
        DataLoadingEffectWithPagination<T>()

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

    val isNextPageLoading: Boolean
        get() = this is NextPageLoading
    val isNextPageLoaded: Boolean
        get() = this is NextPageLoaded
    val isNextPageLoadingError: Boolean
        get() = this is NextPageLoadingError
}
package com.aya.digital.core.data.base.dataprocessing.dataloading

import com.aya.digital.core.data.base.dataprocessing.dataloading.enums.OperationState
import com.aya.digital.core.networkbase.server.RequestResult

sealed class DataLoadingEffect2<T> {
    @JvmInline
    value class ListWrapper<T>(val payload: List<T>)

    class Loading<T> : DataLoadingEffect2<T>()
    data class Loaded<T>(val payload: T) : DataLoadingEffect2<T>()

    data class LoadingError<T>(val throwable: RequestResult.Error) : DataLoadingEffect2<T>()

    class Refreshing<T> : DataLoadingEffect2<T>()
    data class Refreshed<T>(val payload: T) : DataLoadingEffect2<T>()

    data class RefreshingError<T>(val throwable: RequestResult.Error) : DataLoadingEffect2<T>()

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

    val inProgress: Boolean
        get() = this is Loading || this is Refreshing

    val loadingOp: DataLoadingOperation
        get() = when (this) {
            is Loaded, is Refreshed -> DataLoadingOperation.Idle
            is Loading, is Refreshing -> DataLoadingOperation.LoadingData(OperationState.PROGRESS)
            is LoadingError, is RefreshingError ->
                DataLoadingOperation.LoadingData(OperationState.NETWORK_ERROR)
        }

    val dataOrNull: T?
        get() = when (this) {
            is Loaded -> this.payload
            is Refreshed -> this.payload
            is Loading, is Refreshing -> null
            is LoadingError, is RefreshingError -> null
        }

    val errorOrNull: RequestResult.Error?
        get() = when (this) {
            is LoadingError -> throwable
            is RefreshingError -> throwable
            is Loaded -> null
            is Loading -> null
            is Refreshed -> null
            is Refreshing -> null
        }
}
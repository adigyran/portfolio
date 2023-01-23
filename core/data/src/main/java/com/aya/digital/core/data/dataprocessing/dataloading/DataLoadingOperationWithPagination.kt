package com.aya.digital.core.data.dataprocessing.dataloading

import android.os.Parcelable
import com.aya.digital.core.data.dataprocessing.enums.OperationState
import kotlinx.parcelize.Parcelize

sealed class DataLoadingOperationWithPagination : Parcelable {
    @Parcelize
    object Idle : DataLoadingOperationWithPagination()

    @Parcelize
    class LoadingData(val operationState: OperationState) : DataLoadingOperationWithPagination(),
        Parcelable

    @Parcelize
    class RefreshingData(val operationState: OperationState) : DataLoadingOperationWithPagination(),
        Parcelable

    @Parcelize
    class NextPageLoading(val operationState: OperationState) :
        DataLoadingOperationWithPagination(), Parcelable

    val isLoading: Boolean
        get() = (this as? LoadingData)?.operationState == OperationState.PROGRESS
    val isLoadingError: Boolean
        get() = (this as? LoadingData)?.operationState == OperationState.NETWORK_ERROR

    val isRefreshing: Boolean
        get() = (this as? RefreshingData)?.operationState == OperationState.PROGRESS
    val isRefreshingError: Boolean
        get() = (this as? RefreshingData)?.operationState == OperationState.NETWORK_ERROR

    val isNextPageLoading: Boolean
        get() = (this as? NextPageLoading)?.operationState == OperationState.PROGRESS
    val isNextPageLoadingError: Boolean
        get() = (this as? NextPageLoading)?.operationState == OperationState.NETWORK_ERROR

    val isIdle: Boolean
        get() = this is Idle
}
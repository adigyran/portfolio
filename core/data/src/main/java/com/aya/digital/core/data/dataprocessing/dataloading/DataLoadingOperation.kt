package com.aya.digital.core.data.dataprocessing.dataloading

import android.os.Parcelable
import com.aya.digital.core.data.dataprocessing.enums.OperationState
import kotlinx.parcelize.Parcelize

sealed class DataLoadingOperation : Parcelable {
    @Parcelize
    object Idle : DataLoadingOperation(), Parcelable

    @Parcelize
    class LoadingData(val operationState: OperationState) : DataLoadingOperation(), Parcelable

    @Parcelize
    class RefreshingData(val operationState: OperationState) : DataLoadingOperation(), Parcelable

    val isLoading: Boolean
        get() = (this as? LoadingData)?.operationState == OperationState.PROGRESS
    val inLoadingError: Boolean
        get() = (this as? LoadingData)?.operationState == OperationState.NETWORK_ERROR

    val isRefreshing: Boolean
        get() = (this as? RefreshingData)?.operationState == OperationState.PROGRESS
    val inRefreshingError: Boolean
        get() = (this as? RefreshingData)?.operationState == OperationState.NETWORK_ERROR

    val isIdle: Boolean
        get() = this is Idle

    val isInProgress: Boolean
        get() = isLoading || isRefreshing
}
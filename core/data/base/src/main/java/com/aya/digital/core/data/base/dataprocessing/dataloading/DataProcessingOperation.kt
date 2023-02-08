package com.aya.digital.core.data.base.dataprocessing.dataloading

import android.os.Parcelable
import com.aya.digital.core.data.base.dataprocessing.dataloading.enums.OperationState
import kotlinx.parcelize.Parcelize

sealed class DataProcessingOperation : Parcelable {
    @Parcelize
    object Idle : DataProcessingOperation(), Parcelable

    @Parcelize
    class ProcessingData(val operationState: OperationState) : DataProcessingOperation(), Parcelable

    val inProgress: Boolean
        get() = (this as? ProcessingData)?.operationState == OperationState.PROGRESS
    val inError: Boolean
        get() = (this as? ProcessingData)?.operationState == OperationState.NETWORK_ERROR
    val isIdle: Boolean
        get() = this is Idle
}
package com.aya.digital.core.data.base.dataprocessing.dataloading

import com.aya.digital.core.data.base.dataprocessing.dataloading.enums.OperationState
import com.aya.digital.core.networkbase.server.RequestResult

sealed class DataProcessingEffect<T> {
    class Processing<T> : DataProcessingEffect<T>()
    data class Processed<T>(val payload: T) : DataProcessingEffect<T>()
    data class ProcessingError<T>(val throwable: RequestResult.Error) : DataProcessingEffect<T>()

    inline fun <V> processResult(
        processingHandler: () -> V,
        successHandler: (T) -> V,
        errorHandler: (RequestResult.Error) -> V,
    ): V =
        when (this) {
            is Processing -> processingHandler()
            is Processed -> successHandler(this.payload)
            is ProcessingError -> errorHandler(this.throwable)
        }

    val isProcessing: Boolean
        get() = this is Processing
    val isProcessed: Boolean
        get() = this is Processed
    val isError: Boolean
        get() = this is ProcessingError

    val processingOp: DataProcessingOperation
        get() = when (this) {
            is Processed -> DataProcessingOperation.Idle
            is Processing -> DataProcessingOperation.ProcessingData(OperationState.PROGRESS)
            is ProcessingError -> DataProcessingOperation.ProcessingData(OperationState.NETWORK_ERROR)
        }

    val dataOrNull: T?
        get() = when (this) {
            is Processed -> this.payload
            is Processing -> null
            is ProcessingError -> null
        }

    val errorOrNull: RequestResult.Error?
        get() = when (this) {
            is Processed -> null
            is Processing -> null
            is ProcessingError -> this.throwable
        }
}
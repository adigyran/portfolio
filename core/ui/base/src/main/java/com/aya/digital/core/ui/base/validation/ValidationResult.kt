package com.aya.digital.core.ui.base.validation

sealed class ValidationResult {
    object Ok : ValidationResult()
    data class Error(val errorMsgId: Int) : ValidationResult()

    inline fun <V> processResult(
        successHandler: (Ok) -> V,
        errorHandler: (Error) -> V,
    ): V =
        when (this) {
            is Ok -> successHandler(this)
            is Error -> errorHandler(this)
        }

    fun isOk(): Boolean =
        this is Ok

    fun isError(): Boolean =
        this is Error

    fun ValidationResult.processResultMessage() =
        this.processResult({ null }, { it.errorMsgId })


}
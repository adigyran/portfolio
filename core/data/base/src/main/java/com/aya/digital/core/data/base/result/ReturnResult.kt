package com.aya.digital.core.data.base.result

sealed class ReturnResult<T : Any>(open val requestCode: String) {
    data class Cancelled<T : Any>(override val requestCode: String) : ReturnResult<T>(requestCode)
    data class Ok<T : Any>(override val requestCode: String, val res: T) :
        ReturnResult<T>(requestCode)

    inline fun <V> processResult(
        successHandler: (Ok<T>) -> V, cancelHandler: (Cancelled<T>) -> V
    ): V =
        when (this) {
            is Ok -> successHandler(this)
            is Cancelled -> cancelHandler(this)
        }
}
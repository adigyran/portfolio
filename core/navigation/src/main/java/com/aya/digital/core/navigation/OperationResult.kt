package com.aya.digital.core.navigation

sealed class OperationResult<X, Y> {
    data class Ok<X, Y>(val payload: X) : OperationResult<X, Y>()
    data class Fail<X, Y>(val payload: Y) : OperationResult<X, Y>()
}
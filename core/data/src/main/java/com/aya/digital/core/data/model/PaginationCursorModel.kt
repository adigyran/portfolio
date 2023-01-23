package com.aya.digital.core.data.model

data class PaginationCursorModel<T>(
    var results: List<T> = listOf(),
    var nextPage: String?,
    var previousPage: String?
)
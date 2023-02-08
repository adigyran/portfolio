package com.aya.digital.core.data.base.dataprocessing

data class PaginationCursorModel<T>(
    var results: List<T> = listOf(),
    var nextPage: String?,
    var previousPage: String?
)
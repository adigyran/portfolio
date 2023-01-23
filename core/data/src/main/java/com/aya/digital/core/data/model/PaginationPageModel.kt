package com.aya.digital.core.data.model

data class PaginationPageModel<T>(
    var results: List<T> = listOf(),
    var nextPage: Int?,
    var previousPage: Int?
)
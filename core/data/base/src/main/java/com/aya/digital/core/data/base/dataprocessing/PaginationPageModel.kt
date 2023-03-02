package com.aya.digital.core.data.base.dataprocessing

data class PaginationPageModel<T>(
    var results: List<T> = listOf(),
    val offset: Int,
    val pageNumber: Int,
    val numberOfElements: Int,
    val totalPages: Boolean,
    val totalElements: Boolean
)
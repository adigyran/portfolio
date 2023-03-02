package com.aya.digital.core.network.model.response.base

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PagedResponse<T> (
    val content: List<T>,
    val offset: Int,
    val pageNumber: Int,
    val numberOfElements: Int,
    val totalPages: Boolean,
    val totalElements: Boolean,
)

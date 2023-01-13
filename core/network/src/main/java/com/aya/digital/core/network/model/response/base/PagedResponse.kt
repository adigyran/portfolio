package com.aya.digital.core.network.model.response.base

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PagedResponse<T> (
    val content: List<T>?,
    val number: Int?,
    val totalPages: Int?,
    val totalElements: Int?,
    val first: Boolean?,
    val last: Boolean?,
)
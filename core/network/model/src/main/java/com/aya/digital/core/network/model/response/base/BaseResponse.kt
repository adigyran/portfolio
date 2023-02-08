package com.aya.digital.core.network.model.response.base

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pageable(
    val offset: Int?,
    val pageNumber: Int?,
    val pageSize: Int?,
    val paged: Boolean?,
    val sort: com.aya.digital.core.network.model.response.base.Sort?,
    val unpaged: Boolean?
)
@JsonClass(generateAdapter = true)
data class SortX(
    val empty: Boolean?,
    val sorted: Boolean?,
    val unsorted: Boolean?
)
@JsonClass(generateAdapter = true)
data class Sort(
    val empty: Boolean?,
    val sorted: Boolean?,
    val unsorted: Boolean?
)
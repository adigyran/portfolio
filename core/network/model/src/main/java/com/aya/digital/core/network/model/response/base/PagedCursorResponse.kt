package com.aya.digital.core.network.model.response.base

import com.squareup.moshi.JsonClass

/*    "scrollToken": "FGluY2x1ZGVfY29udGV4dF91dWlkDXF1ZXJ5QW5kRmV0Y2gBFmk1WHBIYlpvUWEya3N5QTExVGU1d2cAAAAAAAAAuhZSODluaHpGelJMMm9IRl93bXNkSnR3",
    "scrollTime": "1m",
    "sizeResult": 10,
    "data": [*/
@JsonClass(generateAdapter = true)
class PagedCursorResponse<T> (
    val data: List<T>,
    val scrollTime: String,
    val scrollToken: String?,
    val sizeResult: Int
)

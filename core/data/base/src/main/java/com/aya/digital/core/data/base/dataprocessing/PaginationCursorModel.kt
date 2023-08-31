package com.aya.digital.core.data.base.dataprocessing


/*    "scrollToken": "FGluY2x1ZGVfY29udGV4dF91dWlkDXF1ZXJ5QW5kRmV0Y2gBFmk1WHBIYlpvUWEya3N5QTExVGU1d2cAAAAAAAAAuhZSODluaHpGelJMMm9IRl93bXNkSnR3",
    "scrollTime": "1m",
    "sizeResult": 10,
    "data": [*/
data class PaginationCursorModel<T>(
    var data: List<T> = listOf(),
    var scrollToken: String?,
    var sizeResult: Int?,
    val totalResults:Int = 0
)
package com.aya.digital.core.domain.dictionaries.base.model

data class MultiSelectItemPaginationModel(
    val cursor:String?, val items:List<MultiSelectItem>
) {
}
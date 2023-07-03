package com.aya.digital.core.domain.dictionaries.cities.model

import com.aya.digital.core.domain.dictionaries.base.model.ItemPaginationModel
import com.aya.digital.core.domain.dictionaries.base.model.ItemPaginationModelWithCursor

data class CityItemPaginationModel(
    private val cursor:String?,private val items:List<CityItem>
): ItemPaginationModelWithCursor {
    override fun getCursor(): String? = cursor

    override fun getItems(): List<ItemPaginationModel> = items
}
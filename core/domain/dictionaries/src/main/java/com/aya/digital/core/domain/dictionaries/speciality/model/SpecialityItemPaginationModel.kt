package com.aya.digital.core.domain.dictionaries.speciality.model

import com.aya.digital.core.domain.dictionaries.base.model.ItemPaginationModel
import com.aya.digital.core.domain.dictionaries.base.model.ItemPaginationModelWithCursor

data class SpecialityItemPaginationModel(
    private val cursor:String?,private val items:List<SpecialityItem>
): ItemPaginationModelWithCursor {
    override fun getCursor(): String? = cursor

    override fun getItems(): List<ItemPaginationModel> = items
}
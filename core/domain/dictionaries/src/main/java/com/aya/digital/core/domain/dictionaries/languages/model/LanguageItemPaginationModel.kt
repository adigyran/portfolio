package com.aya.digital.core.domain.dictionaries.languages.model

import com.aya.digital.core.domain.dictionaries.base.model.ItemPaginationModel
import com.aya.digital.core.domain.dictionaries.base.model.ItemPaginationModelWithCursor

data class LanguageItemPaginationModel(
    private val cursor:String?,private val items:List<LanguageItem>
): ItemPaginationModelWithCursor {
    override fun getCursor(): String? = cursor

    override fun getItems(): List<ItemPaginationModel> = items
}
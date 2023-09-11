package com.aya.digital.core.domain.dictionaries.medicaldegrees.model

import com.aya.digital.core.domain.dictionaries.base.model.ItemPaginationModel
import com.aya.digital.core.domain.dictionaries.base.model.ItemPaginationModelWithCursor

data class MedicalDegreeItemPaginationModel(
    private val cursor:String?,private val items:List<MedicalDegreeItem>
): ItemPaginationModelWithCursor {
    override fun getCursor(): String? = cursor

    override fun getItems(): List<ItemPaginationModel> = items
}
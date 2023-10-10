package com.aya.digital.core.domain.dictionaries.emergencycontacttype.model

import com.aya.digital.core.domain.dictionaries.base.model.ItemPaginationModel
import com.aya.digital.core.domain.dictionaries.base.model.ItemPaginationModelWithCursor

data class EmergencyContactTypeItemPaginationModel(
    private val cursor:String?,private val items:List<EmergencyContactTypeItem>
): ItemPaginationModelWithCursor {
    override fun getCursor(): String? = cursor

    override fun getItems(): List<ItemPaginationModel> = items
}
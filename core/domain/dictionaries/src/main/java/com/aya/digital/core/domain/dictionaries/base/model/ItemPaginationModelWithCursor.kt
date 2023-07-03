package com.aya.digital.core.domain.dictionaries.base.model

interface ItemPaginationModelWithCursor {
    fun getCursor():String?
    fun getItems():List<ItemPaginationModel>
}
open class ItemPaginationModel(open val id:Int, open val name:String)
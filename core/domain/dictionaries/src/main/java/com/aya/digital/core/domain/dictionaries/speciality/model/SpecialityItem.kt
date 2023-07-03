package com.aya.digital.core.domain.dictionaries.speciality.model

import com.aya.digital.core.domain.dictionaries.base.model.ItemPaginationModel

data class SpecialityItem(
    override val id: Int,
    override val name: String,
    val code:String
): ItemPaginationModel(id, name) {
}
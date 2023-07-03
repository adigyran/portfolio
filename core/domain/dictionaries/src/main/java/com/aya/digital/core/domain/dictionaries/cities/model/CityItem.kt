package com.aya.digital.core.domain.dictionaries.cities.model

import com.aya.digital.core.domain.dictionaries.base.model.ItemPaginationModel

data class CityItem(
    override val id: Int,
    override val name: String
):ItemPaginationModel(id, name) {
}
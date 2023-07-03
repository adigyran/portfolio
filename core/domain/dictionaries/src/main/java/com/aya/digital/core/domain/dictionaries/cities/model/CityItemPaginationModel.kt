package com.aya.digital.core.domain.dictionaries.cities.model

data class CityItemPaginationModel(
    val cursor:String?, val items:List<CityItem>
)
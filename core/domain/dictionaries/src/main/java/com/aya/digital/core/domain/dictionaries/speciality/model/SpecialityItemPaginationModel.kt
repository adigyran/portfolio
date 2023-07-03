package com.aya.digital.core.domain.dictionaries.speciality.model

data class SpecialityItemPaginationModel(
    val cursor:String?, val items:List<SpecialityItem>
)
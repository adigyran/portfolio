package com.aya.digital.core.domain.dictionaries.insurancecompany.model

data class InsuranceCompanyItemPaginationModel(
    val cursor:String?, val items:List<InsuranceCompanyItem>
)
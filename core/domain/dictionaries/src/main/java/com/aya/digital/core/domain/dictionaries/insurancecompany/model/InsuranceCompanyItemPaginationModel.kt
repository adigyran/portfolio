package com.aya.digital.core.domain.dictionaries.insurancecompany.model

import com.aya.digital.core.domain.dictionaries.base.model.ItemPaginationModel
import com.aya.digital.core.domain.dictionaries.base.model.ItemPaginationModelWithCursor

data class InsuranceCompanyItemPaginationModel(
    private val cursor: String?, private val items: List<InsuranceCompanyItem>
) : ItemPaginationModelWithCursor {
    override fun getCursor(): String? = cursor

    override fun getItems(): List<ItemPaginationModel> = items
}
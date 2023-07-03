package com.aya.digital.core.domain.dictionaries.insurancecompany.model

import com.aya.digital.core.domain.dictionaries.base.model.ItemPaginationModel

data class InsuranceCompanyItem(
    override val id: Int,
    override val name: String
): ItemPaginationModel(id, name) {
}
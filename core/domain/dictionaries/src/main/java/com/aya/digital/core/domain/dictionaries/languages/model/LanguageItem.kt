package com.aya.digital.core.domain.dictionaries.languages.model

import com.aya.digital.core.domain.dictionaries.base.model.ItemPaginationModel

data class LanguageItem(
    override val id: Int,
    override val name: String
):ItemPaginationModel(id, name) {
}
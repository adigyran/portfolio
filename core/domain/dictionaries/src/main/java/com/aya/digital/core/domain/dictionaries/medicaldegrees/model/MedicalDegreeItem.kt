package com.aya.digital.core.domain.dictionaries.medicaldegrees.model

import com.aya.digital.core.domain.dictionaries.base.model.ItemPaginationModel

data class MedicalDegreeItem(
    override val id: Int,
    override val name: String
):ItemPaginationModel(id, name) {
}
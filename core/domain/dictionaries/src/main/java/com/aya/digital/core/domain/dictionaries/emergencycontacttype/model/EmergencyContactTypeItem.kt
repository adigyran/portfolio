package com.aya.digital.core.domain.dictionaries.emergencycontacttype.model

import com.aya.digital.core.domain.dictionaries.base.model.ItemPaginationModel

data class EmergencyContactTypeItem(
    override val id: Int,
    override val name: String
):ItemPaginationModel(id, name) {
}
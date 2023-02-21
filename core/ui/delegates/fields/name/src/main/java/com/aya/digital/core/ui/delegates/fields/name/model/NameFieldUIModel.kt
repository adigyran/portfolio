package com.aya.digital.core.ui.delegates.fields.name.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.adapters.base.FieldItem

class NameFieldUIModel : FieldItem {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is NameFieldUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is NameFieldUIModel
}
package com.aya.digital.core.ui.delegates.fields.selection.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.adapters.base.FieldItem

class SelectionFieldUIModel : FieldItem {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is SelectionFieldUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is SelectionFieldUIModel
}
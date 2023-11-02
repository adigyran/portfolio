package com.aya.digital.core.ui.delegates.components.fields.selection.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.adapters.base.FieldItem

class SelectionFieldUIModel(
    val tag: Int,
    label: String,
    text: String?,
    error: String?,
    val endIconRes: Int? = null
) : FieldItem(
    label, text,
    error
) {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        super.areItemsTheSame(newItem) && newItem is SelectionFieldUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        super.areContentsTheSame(newItem) && newItem is SelectionFieldUIModel && this.tag == newItem.tag && this.endIconRes == newItem.endIconRes && this.label == newItem.label
                && this.text == newItem.text
}
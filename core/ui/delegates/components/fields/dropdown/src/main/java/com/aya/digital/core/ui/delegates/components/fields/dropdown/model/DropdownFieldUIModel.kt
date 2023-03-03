package com.aya.digital.core.ui.delegates.components.fields.selection.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.adapters.base.FieldItem

class DropdownFieldUIModel(val tag:Int, val items: List<String>?, label: String, text:String = "",  error: String?) : FieldItem(label, text,
    error
) {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
       super.areItemsTheSame(newItem) && newItem is DropdownFieldUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        super.areContentsTheSame(newItem) && newItem is DropdownFieldUIModel && this.tag == newItem.tag && this.items == newItem.items
}
package com.aya.digital.core.ui.adapters.base

abstract class FieldItem(val label: String, val text: String?, val error:String?) : DiffItem {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is FieldItem

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is FieldItem && this.label == newItem.label && this.text == newItem.text
                && this.error == newItem.error
}
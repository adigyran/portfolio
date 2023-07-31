package com.aya.digital.core.ui.delegates.components.fields.name.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.adapters.base.FieldItem

 class NameFieldUIModel(val tag:Int,label: String, text: String?, error: String?,val suffix:String?=null) : FieldItem(label, text, error) {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        super.areItemsTheSame(newItem) && newItem is NameFieldUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        super.areContentsTheSame(newItem) &&
                newItem is NameFieldUIModel && this.tag == newItem.tag && this.suffix == newItem.suffix
}
package com.aya.digital.core.ui.delegates.components.fields.emailphone.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.adapters.base.FieldItem

class EmailFieldUIModel(
    val tag: Int,
    label: String,
    text: String?,
    error: String?,
) : FieldItem(
    label, text,
    error
) {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        super.areItemsTheSame(newItem) && newItem is EmailFieldUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        super.areContentsTheSame(newItem) && newItem is EmailFieldUIModel
                && this.tag == newItem.tag


}

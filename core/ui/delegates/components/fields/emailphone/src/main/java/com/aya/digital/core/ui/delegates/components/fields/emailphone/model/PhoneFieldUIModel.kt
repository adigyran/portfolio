package com.aya.digital.core.ui.delegates.components.fields.emailphone.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.adapters.base.FieldItem
import ru.tinkoff.decoro.MaskImpl

class PhoneFieldUIModel(
    val tag: Int,
    label: String,
    text: String?,
    error: String?,
    val mask: MaskImpl
) : FieldItem(
    label, text,
    error
) {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        super.areItemsTheSame(newItem) && newItem is PhoneFieldUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        super.areContentsTheSame(newItem) && newItem is PhoneFieldUIModel
                && this.tag == newItem.tag && this.mask == newItem.mask


}

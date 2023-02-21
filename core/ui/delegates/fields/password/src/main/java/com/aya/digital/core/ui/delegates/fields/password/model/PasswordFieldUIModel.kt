package com.aya.digital.core.ui.delegates.fields.emailphone.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.adapters.base.FieldItem

class PasswordFieldUIModel : FieldItem {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is PasswordFieldUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is PasswordFieldUIModel
}
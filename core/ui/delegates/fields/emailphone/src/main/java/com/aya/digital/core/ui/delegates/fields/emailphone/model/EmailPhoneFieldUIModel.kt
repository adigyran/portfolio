package com.aya.digital.core.ui.delegates.fields.emailphone.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.adapters.base.FieldItem

class EmailPhoneFieldUIModel : FieldItem {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is EmailPhoneFieldUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is EmailPhoneFieldUIModel
}
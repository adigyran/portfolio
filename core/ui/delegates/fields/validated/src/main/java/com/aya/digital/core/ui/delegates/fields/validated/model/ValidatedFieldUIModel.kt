package com.aya.digital.core.ui.delegates.fields.validated.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.adapters.base.FieldItem

class ValidatedFieldUIModel : FieldItem {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is ValidatedFieldUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is ValidatedFieldUIModel
}
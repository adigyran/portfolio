package com.aya.digital.core.ui.delegates.components.fields.validated.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.adapters.base.FieldItem

class ValidatedFieldUIModel(label: String, text: String?, error: String?) : FieldItem(label, text,
    error
) {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        super.areItemsTheSame(newItem) && newItem is ValidatedFieldUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
       super.areContentsTheSame(newItem) && newItem is ValidatedFieldUIModel
}
package com.aya.digital.core.ui.delegates.components.labels.spannablehelper.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.adapters.base.FieldItem
import com.aya.digital.core.ui.base.ext.SpannableObject

data class SpannableHelperLabelUIModel(val formattedText:String, val spannableObject: SpannableObject) : DiffItem {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is SpannableHelperLabelUIModel && this.formattedText == newItem.formattedText

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is SpannableHelperLabelUIModel && this.formattedText == newItem.formattedText
                && this.spannableObject == newItem.spannableObject
}
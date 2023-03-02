package com.aya.digital.core.ui.delegates.features.choosers.multiselect.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.adapters.base.FieldItem

class MultiSelectItemUIModel(val id:Int, val text : String, val selected : Boolean) : DiffItem {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
     newItem is MultiSelectItemUIModel && this.id == newItem.id

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
       newItem is MultiSelectItemUIModel && this.id == newItem.id && this.text == newItem.text && this.selected == newItem.selected
}
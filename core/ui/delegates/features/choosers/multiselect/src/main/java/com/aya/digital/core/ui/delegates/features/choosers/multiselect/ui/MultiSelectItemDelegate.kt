package com.aya.digital.core.ui.delegates.features.choosers.multiselect.ui

import android.graphics.drawable.Drawable
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ui.base.ext.drawables

import com.aya.digital.core.ui.delegates.features.choosers.multiselect.databinding.ItemMultiselectBinding
import com.aya.digital.core.ui.delegates.features.choosers.multiselect.model.MultiSelectItemUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun multiSelectItemDelegate(delegateListeners: MultiSelectItemDelegateListeners) = adapterDelegateViewBinding<MultiSelectItemUIModel,DiffItem,ItemMultiselectBinding>(
    { layoutInflater, root -> ItemMultiselectBinding.inflate(layoutInflater, root, false) }
) {

    binding.root bindClick {delegateListeners.itemClick(item.id)}

    fun getDrawable(selected: Boolean): Drawable
    {
        val id = if(selected) R.drawable.ic_checkbox_checked else R.drawable.ic_checkbox
        return context.drawables[id]
    }

    bind {
       binding.textView.text = item.text
       binding.selectedBox.setImageDrawable(getDrawable(item.selected))
    }



}

class MultiSelectItemDelegateListeners(val itemClick: (id: Int) -> Unit)

package com.aya.digital.core.ui.delegates.components.fields.password.ui

import android.text.Editable
import android.text.Spanned
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.aya.digital.core.ext.colors
import com.aya.digital.core.ext.strings
import com.aya.digital.core.localisation.R
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.base.ext.createSpannableText
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
import com.aya.digital.core.ui.delegates.components.fields.password.model.PasswordFieldUIModel
import com.aya.digital.core.ui.delegates.components.fields.password.databinding.ItemPasswordFieldBinding

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


class PasswordFieldDelegate(private val delegateListeners: PasswordFieldDelegateListeners) :
    BaseDelegate<PasswordFieldUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is PasswordFieldUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<PasswordFieldUIModel> {
        val binding =
            ItemPasswordFieldBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemPasswordFieldBinding) :
        BaseViewHolder<PasswordFieldUIModel>(binding.root) {

        var fieldSet = false
        var valueSet = false
        init {
            binding.edField.doAfterTextChanged { text ->
                delegateListeners.inputFieldChangeListener(item.tag,text.toString())
            }
        }

        override fun bind(item: PasswordFieldUIModel) {
            super.bind(item)
            if (!fieldSet) {
                binding.tilField.hint = item.label
                fieldSet = true
            }
            if(!valueSet && item.text!=null && item.text!!.isNotEmpty() && item.text!=binding.edField.text.toString() )
            {
                binding.edField.setText(item.text)
                valueSet = true
            }
        }
    }

}

class PasswordFieldDelegateListeners(val inputFieldChangeListener: ((tag: Int,text:String) -> Unit))

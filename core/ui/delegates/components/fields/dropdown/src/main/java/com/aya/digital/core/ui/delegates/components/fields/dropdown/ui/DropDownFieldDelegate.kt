package com.aya.digital.core.ui.delegates.components.fields.dropdown.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.dropdown.R
import com.aya.digital.core.ui.delegates.components.fields.dropdown.databinding.ItemDropdownFieldBinding
import com.aya.digital.core.ui.delegates.components.fields.dropdown.model.AutoCompleteItem
import com.aya.digital.core.ui.delegates.components.fields.dropdown.model.DropdownFieldUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import timber.log.Timber

class DropDownFieldDelegate(private val delegateListeners: DropDownFieldDelegateListeners) :
    BaseDelegate<DropdownFieldUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is DropdownFieldUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<DropdownFieldUIModel> {
        val binding =
            ItemDropdownFieldBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemDropdownFieldBinding) :
        BaseViewHolder<DropdownFieldUIModel>(binding.root) {

        var fieldSet = false

        val textWatcher = object : TextWatcher {
            var firstCall = true
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (firstCall) {
                    firstCall = false
                    return
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun afterTextChanged(s: Editable) = Unit
        }
        val adapter = ArrayAdapter(
            binding.root.context,
            R.layout.list_item,
            mutableListOf<AutoCompleteItem>()
        )

        init {
            binding.edField.setAdapter(adapter)
            binding.edField.setOnItemClickListener { adapterView, view, position, id ->
                val selected = adapter.getItem(position)
                selected?.let {
                    delegateListeners.itemSelected(item.tag, it)
                }
            }
        }

        override fun bind(item: DropdownFieldUIModel) {
            super.bind(item)

            if (!fieldSet) {
                binding.tilField.hint = item.label
                item.items?.let {
                    adapter.addAll(it)
                }
                fieldSet = true
            }
            binding.edField.removeTextChangedListener(textWatcher)
            if (binding.edField.text.toString() != item.text) {
                binding.edField.setText(item.text)
            }
            binding.edField.addTextChangedListener(textWatcher)
        }
    }
}

class DropDownFieldDelegateListeners(val itemSelected: (tag: Int, selectedItem: AutoCompleteItem) -> Unit)

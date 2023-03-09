package com.aya.digital.core.ui.delegates.components.fields.dropdown.ui

import android.widget.ArrayAdapter
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.dropdown.R
import com.aya.digital.core.ui.delegates.components.fields.dropdown.databinding.ItemDropdownFieldBinding
import com.aya.digital.core.ui.delegates.components.fields.dropdown.model.AutoCompleteItem
import com.aya.digital.core.ui.delegates.components.fields.dropdown.model.DropdownFieldUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import timber.log.Timber

fun dropDownFieldDelegate(delegateListeners: DropDownFieldDelegateListeners) =
    adapterDelegateViewBinding<DropdownFieldUIModel, DiffItem, ItemDropdownFieldBinding>(
        { layoutInflater, root -> ItemDropdownFieldBinding.inflate(layoutInflater, root, false) }
    ) {

        var fieldSet = false
        var valueSet = false



        val adapter = ArrayAdapter(binding.root.context, R.layout.list_item, mutableListOf<AutoCompleteItem>())
        binding.edField.setAdapter(adapter)

        binding.edField.setOnItemClickListener { adapterView, view, position, id ->
            Timber.d("$position $id")
            val selected = adapter.getItem(position)
            selected?.let {
                Timber.d("{${selected.tag}}")
                delegateListeners.itemSelected(item.tag,it)
            }

           /* val selected = adapterView.getItemAtPosition(position) as String
            val pos: Int = Arrays.asList(regions).indexOf(selected)
            delegateListeners.itemSelected(item.tag, binding.edField.text.toString())
            adapter.getItem()*/
        }

        bind {
            if (!fieldSet) {
                binding.tilField.hint = item.label
                item.items?.let {
                    adapter.addAll(it)
                }
                fieldSet = true
            }
            if (!valueSet && item.text != null && item.text!!.isNotBlank()) {
                binding.edField.setText(item.text)
                valueSet = true
            }
        }
    }

class DropDownFieldDelegateListeners(val itemSelected: (tag: Int, selectedItem: AutoCompleteItem) -> Unit)

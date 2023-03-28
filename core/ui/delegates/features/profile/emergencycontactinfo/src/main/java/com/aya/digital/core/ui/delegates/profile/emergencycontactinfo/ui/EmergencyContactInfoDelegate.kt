package com.aya.digital.core.ui.delegates.profile.info.ui


import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.profile.emergencycontact.databinding.ItemEmergencyContactInfoBinding
import com.aya.digital.core.ui.delegates.profile.info.model.EmergencyContactInfoUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding



class EmergencyContactInfoDelegate() :
    BaseDelegate<EmergencyContactInfoUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is EmergencyContactInfoUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<EmergencyContactInfoUIModel> {
        val binding =
            ItemEmergencyContactInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemEmergencyContactInfoBinding) :
        BaseViewHolder<EmergencyContactInfoUIModel>(binding.root) {

        var initialised = false

        override fun bind(item: EmergencyContactInfoUIModel) {
            super.bind(item)
            if(!initialised) {
                binding.title.text = item.label
                initialised = true
            }
            binding.value.text = item.value
        }
    }
}
package com.aya.digital.core.ui.delegates.profile.info.ui


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.profile.generalinfo.databinding.ItemProfileInfoBinding
import com.aya.digital.core.ui.delegates.profile.info.model.ProfileInfoUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


class ProfileInfoDelegate() :
    BaseDelegate<ProfileInfoUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is ProfileInfoUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<ProfileInfoUIModel> {
        val binding =
            ItemProfileInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemProfileInfoBinding) :
        BaseViewHolder<ProfileInfoUIModel>(binding.root) {

        var initialised = false
        override fun bind(item: ProfileInfoUIModel) {
            super.bind(item)
            if (!initialised) {
                binding.title.text = item.label
                initialised = true
            }
            binding.value.text = item.value
        }
    }

}
package com.aya.digital.core.ui.delegates.profile.main.ui


import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.profile.main.databinding.ItemProfileMainBinding
import com.aya.digital.core.ui.delegates.profile.info.model.ProfileMainUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class ProfileMainDelegate(private val delegateListeners: ProfileMainDelegateListeners) :
    BaseDelegate<ProfileMainUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is ProfileMainUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<ProfileMainUIModel> {
        val binding =
            ItemProfileMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemProfileMainBinding) :
        BaseViewHolder<ProfileMainUIModel>(binding.root) {


        init {
            binding.root bindClick { delegateListeners.onClick(item.tag) }
        }

        override fun bind(item: ProfileMainUIModel) {
            super.bind(item)
            binding.icon.setImageResource(item.icon)
            binding.title.text = item.value
        }
    }

}

class ProfileMainDelegateListeners(val onClick: ((Int) -> Unit))

package com.aya.digital.core.ui.delegates.profile.securitysummary.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.profile.securitysummary.databinding.ItemSecuritySummaryBinding
import com.aya.digital.core.ui.delegates.profile.securitysummary.model.SecuritySummaryUIModel

class SecuritySummaryDelegate(private val itemClick: (tag: Int) -> Unit) :
    BaseDelegate<SecuritySummaryUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is SecuritySummaryUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<SecuritySummaryUIModel> {
        val binding =
            ItemSecuritySummaryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemSecuritySummaryBinding) :
        BaseViewHolder<SecuritySummaryUIModel>(binding.root) {
        var initialised = false
        init {
            binding.root bindClick {itemClick(item.tag)}
        }
        override fun bind(item: SecuritySummaryUIModel) {
            super.bind(item)
            binding.title.text = item.label
            binding.value.text = item.value
        }
    }
}

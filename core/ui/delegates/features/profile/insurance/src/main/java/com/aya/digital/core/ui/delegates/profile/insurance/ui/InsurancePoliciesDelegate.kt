package com.aya.digital.core.ui.delegates.profile.insurance.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.profile.insurance.databinding.ItemInsurancePolicyBinding
import com.aya.digital.core.ui.delegates.profile.insurance.model.InsurancePoliciesUIModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class InsurancePoliciesDelegate(private val onPoliciesClick: () -> Unit) :
    BaseDelegate<InsurancePoliciesUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is InsurancePoliciesUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<InsurancePoliciesUIModel> {
        val binding =
            ItemInsurancePolicyBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemInsurancePolicyBinding) :
        BaseViewHolder<InsurancePoliciesUIModel>(binding.root) {

        init {
            binding.root bindClick { onPoliciesClick() }
        }
        override fun bind(item: InsurancePoliciesUIModel) {
            super.bind(item)
        }
    }
}
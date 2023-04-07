package com.aya.digital.core.ui.delegates.profile.insurance.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.profile.insurance.databinding.ItemInsurancePolicyBinding
import com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model.InsurancePolicyUIModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class InsurancePolicyDelegate(private val onPolicyClick: (id: Int) -> Unit,
                              private val onPolicyMoreClick: (id: Int) -> Unit) :
    BaseDelegate<InsurancePolicyUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is InsurancePolicyUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<InsurancePolicyUIModel> {
        val binding =
            ItemInsurancePolicyBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemInsurancePolicyBinding) :
        BaseViewHolder<InsurancePolicyUIModel>(binding.root) {

        init {
            binding.root bindClick { onPolicyClick(item.id) }
            binding.moreBtn bindClick { onPolicyMoreClick(item.id) }
        }
        override fun bind(item: InsurancePolicyUIModel) {
            super.bind(item)
            binding.name.text = item.name
            binding.number.text = item.number
            Glide
                .with(binding.insuranceCardIv)
                .load(item.photo)
                .transform(
                    CenterCrop(),
                    RoundedCorners(8.dpToPx())
                )
                .dontAnimate()
                .into(binding.insuranceCardIv)
        }
    }
}
package com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.ui

import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.profile.insurance.databinding.ItemInsurancePolicyBinding
import com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model.InsurancePolicyUIModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun insurancePolicyDelegate(
    onPolicyClick: (id: Int) -> Unit,
    onPolicyMoreClick: (id: Int) -> Unit,
) =
    adapterDelegateViewBinding<InsurancePolicyUIModel, DiffItem, ItemInsurancePolicyBinding>(
        { layoutInflater, root ->
            ItemInsurancePolicyBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {
        binding.root bindClick { onPolicyClick(item.id) }
        binding.moreBtn bindClick { onPolicyMoreClick(item.id) }
        bind {
            binding.name.text = item.name
            binding.number.text = item.number
            Glide
                .with(binding.insuranceCardIv)
                .load(item.photo)
                .transform(
                    CenterCrop(),
                    RoundedCorners(12.dpToPx())
                )
                .dontAnimate()
                .into(binding.insuranceCardIv)
        }
    }

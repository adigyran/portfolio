package com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.ui

import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.ext.gone
import com.aya.digital.core.ext.visible
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.profile.insurance.databinding.ItemInsurancePolicyPhotoBinding
import com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model.InsurancePolicyPhotoUIModel
import com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model.InsurancePolicyUIModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun insurancePolicyPhotoDelegate(onPhotoClick: () -> Unit, onPhotoMoreClick: () -> Unit) =
    adapterDelegateViewBinding<InsurancePolicyPhotoUIModel, DiffItem, ItemInsurancePolicyPhotoBinding>(
        { layoutInflater, root ->
            ItemInsurancePolicyPhotoBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {

        var initialised = false
        fun showEmptyPhoto() {
            binding.emptyImageGroup.visible()
            binding.insuranceCardIv.gone()
        }
        binding.uploadPhotoBtn bindClick { onPhotoClick() }
        binding.moreBtn bindClick { onPhotoMoreClick() }
        bind {
            item.photo?.let {
                binding.emptyImageGroup.gone()
                binding.insuranceCardIv.visible()
                Glide
                    .with(binding.insuranceCardIv)
                    .load(item.photo)
                    .transform(
                        CenterCrop(),
                        RoundedCorners(12.dpToPx())
                    )
                    .dontAnimate()
                    .into(binding.insuranceCardIv)
            } ?: showEmptyPhoto()
        }
    }



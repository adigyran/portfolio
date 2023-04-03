package com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.ext.gone
import com.aya.digital.core.ext.visible
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.profile.insurance.databinding.ItemInsurancePolicyPhotoBinding
import com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model.InsurancePolicyPhotoUIModel
import com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.model.InsurancePolicyUIModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


class InsurancePolicyPhotoDelegate(private val onPhotoClick: () -> Unit, private val onPhotoMoreClick: () -> Unit) :
    BaseDelegate<InsurancePolicyPhotoUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is InsurancePolicyPhotoUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<InsurancePolicyPhotoUIModel> {
        val binding =
            ItemInsurancePolicyPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemInsurancePolicyPhotoBinding) :
        BaseViewHolder<InsurancePolicyPhotoUIModel>(binding.root) {

        init {
            binding.emptyImageGroup.gone()
            binding.moreBtn.gone()
            binding.insuranceCardIv.gone()
            binding.uploadPhotoBtn bindClick { onPhotoClick() }
            binding.moreBtn bindClick { onPhotoMoreClick() }
        }

        override fun bind(item: InsurancePolicyPhotoUIModel) {
            super.bind(item)
            item.photo?.let {
                binding.moreBtn.visible()
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

        private fun showEmptyPhoto() {
            binding.emptyImageGroup.visible()
            binding.moreBtn.gone()
            binding.insuranceCardIv.gone()
        }
    }
}


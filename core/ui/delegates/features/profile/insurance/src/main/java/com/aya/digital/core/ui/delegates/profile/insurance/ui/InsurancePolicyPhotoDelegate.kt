package com.aya.digital.core.ui.delegates.profile.insurance.ui

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
import com.aya.digital.core.ui.delegates.profile.insurance.model.InsurancePolicyPhotoUIModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners


class InsurancePolicyPhotoDelegate(private val onPhotoClick: () -> Unit,private val onUploadPhotoClick: () -> Unit, private val onPhotoMoreClick: () -> Unit) :
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
            binding.notEmptyImageGroup.gone()
            binding.uploadPhotoBtn bindClick { onUploadPhotoClick() }
            binding.insuranceCardIv bindClick {onPhotoClick()}
            binding.moreBtn bindClick { onPhotoMoreClick() }
        }

        override fun bind(item: InsurancePolicyPhotoUIModel) {
            super.bind(item)
            item.photo?.let {
                binding.emptyImageGroup.gone()
                binding.notEmptyImageGroup.visible()
                Glide
                    .with(binding.insuranceCardIv)
                    .load(item.photo)
                    .transform(
                        CenterCrop(),
                        RoundedCorners(8.dpToPx())
                    )
                    .dontAnimate()
                    .into(binding.insuranceCardIv)
            } ?: showEmptyPhoto()
        }

        private fun showEmptyPhoto() {
            binding.emptyImageGroup.visible()
            binding.notEmptyImageGroup.gone()
        }
    }
}


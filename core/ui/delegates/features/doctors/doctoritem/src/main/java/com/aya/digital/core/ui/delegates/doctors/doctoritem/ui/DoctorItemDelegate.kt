package com.aya.digital.core.ui.delegates.doctors.doctoritem.ui

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.colors
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.ext.gone
import com.aya.digital.core.ext.visible
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.base.ext.createTouchableSpan
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
import com.aya.digital.core.ui.delegates.doctors.doctoritem.model.DoctorItemUIModel
import com.aya.digital.core.ui.delegates.features.doctors.doctoritem.databinding.ItemDoctorBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class DoctorItemDelegate(val onDoctorClicked: (doctorId:Int) -> Unit) :
    BaseDelegate<DoctorItemUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is DoctorItemUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<DoctorItemUIModel> {
        val binding =
            ItemDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemDoctorBinding) :
        BaseViewHolder<DoctorItemUIModel>(binding.root) {

        init {
            binding.root bindClick {onDoctorClicked(item.id)}
        }
        override fun bind(item: DoctorItemUIModel) {
            super.bind(item)
            binding.tvName.text = item.name
            binding.tvSpeciality.text = item.speciality
            item.photo?.let {
                Glide
                    .with(binding.ivAvatar)
                    .load(item.photo)
                    .transform(
                       CircleCrop()
                    )
                    .dontAnimate()
                    .into(binding.ivAvatar)
            }
        }

    }
}
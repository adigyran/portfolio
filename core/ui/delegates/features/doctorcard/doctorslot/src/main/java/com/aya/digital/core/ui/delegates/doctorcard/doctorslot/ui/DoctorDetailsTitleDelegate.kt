package com.aya.digital.core.ui.delegates.doctorcard.doctorslot.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.model.DoctorDetailsTitleUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.model.DoctorSlotUIModel
import com.aya.digital.core.ui.delegates.features.doctorcard.doctorslot.databinding.ItemDoctorDetailsTitleBinding
import com.aya.digital.core.ui.delegates.features.doctorcard.doctorslot.databinding.ItemDoctorSlotBinding

class DoctorDetailsTitleDelegate(private val onSlotClick: (id: Int) -> Unit) :
    BaseDelegate<DoctorDetailsTitleUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is DoctorSlotUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<DoctorDetailsTitleUIModel> {
        val binding =
            ItemDoctorDetailsTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemDoctorDetailsTitleBinding) :
        BaseViewHolder<DoctorDetailsTitleUIModel>(binding.root) {

        init {
            binding.root bindClick { onSlotClick(item.id) }
        }
        override fun bind(item: DoctorDetailsTitleUIModel) {
            super.bind(item)
            binding.tvTitle.text = item.titleText
        }
    }
}
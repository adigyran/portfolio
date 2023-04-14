package com.aya.digital.core.ui.delegates.doctorcard.doctorslot.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.model.DoctorDateTitleUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.model.DoctorSlotUIModel
import com.aya.digital.core.ui.delegates.features.doctorcard.doctorslot.databinding.ItemDoctorDateTitleBinding
import com.aya.digital.core.ui.delegates.features.doctorcard.doctorslot.databinding.ItemDoctorSlotBinding

class DoctorDateTitleDelegate() :
    BaseDelegate<DoctorDateTitleUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is DoctorDateTitleUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<DoctorDateTitleUIModel> {
        val binding =
            ItemDoctorDateTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemDoctorDateTitleBinding) :
        BaseViewHolder<DoctorDateTitleUIModel>(binding.root) {

        init {

        }
        override fun bind(item: DoctorDateTitleUIModel) {
            super.bind(item)
            binding.tvDate.text = item.dateText
        }
    }
}
package com.aya.digital.core.ui.delegates.doctorcard.doctorslot.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.model.DoctorDetailsBioUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.model.DoctorSlotUIModel
import com.aya.digital.core.ui.delegates.features.doctorcard.doctorslot.databinding.ItemDoctorDetailsBioBinding
import com.aya.digital.core.ui.delegates.features.doctorcard.doctorslot.databinding.ItemDoctorSlotBinding

class DoctorDetailsBioDelegate(private val onSlotClick: (id: Int) -> Unit) :
    BaseDelegate<DoctorDetailsBioUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is DoctorDetailsBioUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<DoctorDetailsBioUIModel> {
        val binding =
            ItemDoctorDetailsBioBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemDoctorDetailsBioBinding) :
        BaseViewHolder<DoctorDetailsBioUIModel>(binding.root) {

        init {
            binding.root bindClick { onSlotClick(item.id) }
        }
        override fun bind(item: DoctorDetailsBioUIModel) {
            super.bind(item)

        }
    }
}
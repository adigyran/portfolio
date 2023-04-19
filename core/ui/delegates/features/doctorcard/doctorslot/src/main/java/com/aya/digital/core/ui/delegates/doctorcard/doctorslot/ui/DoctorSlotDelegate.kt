package com.aya.digital.core.ui.delegates.doctorcard.doctorslot.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.model.DoctorSlotUIModel
import com.aya.digital.core.ui.delegates.features.doctorcard.doctorslot.databinding.ItemDoctorSlotBinding
import kotlinx.datetime.LocalDate
import timber.log.Timber

class DoctorSlotDelegate(private val onSlotClick: (id: Int, date : LocalDate?) -> Unit) :
    BaseDelegate<DoctorSlotUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is DoctorSlotUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<DoctorSlotUIModel> {
        val binding =
            ItemDoctorSlotBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemDoctorSlotBinding) :
        BaseViewHolder<DoctorSlotUIModel>(binding.root) {

        init {
            binding.root bindClick { onSlotClick(item.id, item.date) }
        }
        override fun bind(item: DoctorSlotUIModel) {
            super.bind(item)
            binding.tvTime.text = item.timeText
            binding.root.isSelected = item.selected
        }
    }
}
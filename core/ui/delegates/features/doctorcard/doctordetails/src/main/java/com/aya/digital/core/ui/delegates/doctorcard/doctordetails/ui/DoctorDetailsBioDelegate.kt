package com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.DoctorDetailsBioUIModel
import com.aya.digital.core.ui.delegates.features.doctorcard.doctordetails.databinding.ItemDoctorDetailsBioBinding

class DoctorDetailsBioDelegate() :
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
        }
        override fun bind(item: DoctorDetailsBioUIModel) {
            super.bind(item)

        }
    }
}
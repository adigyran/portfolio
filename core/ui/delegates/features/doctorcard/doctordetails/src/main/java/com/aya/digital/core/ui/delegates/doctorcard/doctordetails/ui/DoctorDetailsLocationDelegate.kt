package com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.DoctorDetailsBioUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.DoctorDetailsInsuranceUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.DoctorDetailsLocationUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.DoctorDetailsTitleUIModel
import com.aya.digital.core.ui.delegates.features.doctorcard.doctordetails.databinding.ItemDoctorDetailsInsuranceBinding
import com.aya.digital.core.ui.delegates.features.doctorcard.doctordetails.databinding.ItemDoctorDetailsLocationBinding
import com.aya.digital.core.ui.delegates.features.doctorcard.doctordetails.databinding.ItemDoctorDetailsTitleBinding

class DoctorDetailsLocationDelegate() :
    BaseDelegate<DoctorDetailsLocationUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is DoctorDetailsLocationUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<DoctorDetailsLocationUIModel> {
        val binding =
            ItemDoctorDetailsLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemDoctorDetailsLocationBinding) :
        BaseViewHolder<DoctorDetailsLocationUIModel>(binding.root) {

        init {
        }
        override fun bind(item: DoctorDetailsLocationUIModel) {
            super.bind(item)
            binding.tvAddress.text = item.address
        }
    }
}
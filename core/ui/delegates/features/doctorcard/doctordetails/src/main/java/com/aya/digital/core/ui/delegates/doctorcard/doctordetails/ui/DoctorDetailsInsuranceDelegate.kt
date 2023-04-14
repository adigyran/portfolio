package com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.DoctorDetailsBioUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.DoctorDetailsInsuranceUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.DoctorDetailsTitleUIModel
import com.aya.digital.core.ui.delegates.features.doctorcard.doctordetails.databinding.ItemDoctorDetailsInsuranceBinding
import com.aya.digital.core.ui.delegates.features.doctorcard.doctordetails.databinding.ItemDoctorDetailsTitleBinding

class DoctorDetailsInsuranceDelegate() :
    BaseDelegate<DoctorDetailsInsuranceUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is DoctorDetailsInsuranceUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<DoctorDetailsInsuranceUIModel> {
        val binding =
            ItemDoctorDetailsInsuranceBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemDoctorDetailsInsuranceBinding) :
        BaseViewHolder<DoctorDetailsInsuranceUIModel>(binding.root) {

        init {
        }
        override fun bind(item: DoctorDetailsInsuranceUIModel) {
            super.bind(item)
            binding.tvInsurance.text = item.insuranceName
        }
    }
}
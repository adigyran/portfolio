package com.aya.digital.core.ui.delegates.profile.generalinfo.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.profile.generalinfo.databinding.ItemProfileInfoBinding
import com.aya.digital.core.ui.delegates.features.profile.generalinfo.databinding.ItemProfileMapBinding
import com.aya.digital.core.ui.delegates.profile.generalinfo.model.ProfileInfoUIModel
import com.aya.digital.core.ui.delegates.profile.generalinfo.model.ProfileMapUIModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView


class ProfileMapDelegate() :
    BaseDelegate<ProfileMapUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is ProfileMapUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<ProfileMapUIModel> {
        val binding =
            ItemProfileMapBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemProfileMapBinding) :
        BaseViewHolder<ProfileMapUIModel>(binding.root) {
        private var map: GoogleMap? = null
        private var mMap: MapView? = null

        var initialised = false
        override fun bind(item: ProfileMapUIModel) {
            super.bind(item)

        }
    }

}
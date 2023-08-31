package com.aya.digital.core.feature.tabviews.home.ui

import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter2
import com.aya.digital.core.ui.adapters.base.BaseDiffCallback
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.adapters.base.ScrolledItemPosition
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.HomeButtonItemDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.HomeLastUpdatesBottomDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.HomeLastUpdatesItemDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.HomeLastUpdatesTitleDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.HomeLastUpdatesTopDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.HomeNewsContainerDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

class HomeAdapter(private val onButtonClick: (tag: Int) -> Unit) : BaseDelegateAdapter2() {

    companion object {
        fun create(init: BaseDelegateAdapter.Builder.() -> Unit) = BaseDelegateAdapter.Builder(init)
            .build()
    }

    private val adapterDelegatesManager: AdapterDelegatesManager<List<DiffItem>> =
        AdapterDelegatesManager<List<DiffItem>>().apply {
            addDelegate(HomeButtonItemDelegate(onButtonClick))
            addDelegate(HomeNewsContainerDelegate(RecyclerView.RecycledViewPool()) { recyclerScrollPositions })
            addDelegate(HomeLastUpdatesBottomDelegate())
            addDelegate(HomeLastUpdatesTopDelegate())
            addDelegate(HomeLastUpdatesItemDelegate())
            addDelegate(HomeLastUpdatesTitleDelegate())
        }
    override val adapter: AsyncListDifferDelegationAdapterModified<DiffItem> =
        AsyncListDifferDelegationAdapterModified(BaseDiffCallback(), adapterDelegatesManager)


}
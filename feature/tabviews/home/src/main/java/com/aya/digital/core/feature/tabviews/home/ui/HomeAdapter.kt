package com.aya.digital.core.feature.tabviews.home.ui

import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter2
import com.aya.digital.core.ui.adapters.base.BaseDiffCallback
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.home.homeitems.ui.HomeButtonItemDelegate
import com.aya.digital.core.ui.delegates.home.homeitems.ui.HomeClinicsItemDelegate
import com.aya.digital.core.ui.delegates.home.homeitems.ui.HomeLastUpdatesBottomDelegate
import com.aya.digital.core.ui.delegates.home.homeitems.ui.HomeLastUpdatesItemDelegate
import com.aya.digital.core.ui.delegates.home.homeitems.ui.HomeLastUpdatesTitleDelegate
import com.aya.digital.core.ui.delegates.home.homeitems.ui.HomeLastUpdatesTopDelegate
import com.aya.digital.core.ui.delegates.home.homeitems.ui.HomeNewsContainerDelegate
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
            addDelegate(HomeClinicsItemDelegate())
        }
    override val adapter: AsyncListDifferDelegationAdapterModified<DiffItem> =
        AsyncListDifferDelegationAdapterModified(BaseDiffCallback(), adapterDelegatesManager)


}
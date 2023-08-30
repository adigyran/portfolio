package com.aya.digital.core.feature.tabviews.home.ui

import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter2
import com.aya.digital.core.ui.adapters.base.BaseDiffCallback
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.adapters.base.ScrolledItemPosition
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

class HomeAdapter:BaseDelegateAdapter2() {

    companion object {
        fun create(init: BaseDelegateAdapter.Builder.() -> Unit) = BaseDelegateAdapter.Builder(init)
            .build()
    }
    private val adapterDelegatesManager: AdapterDelegatesManager<List<DiffItem>> =
        AdapterDelegatesManager<List<DiffItem>>().apply {

        }
    override val adapter: AsyncListDifferDelegationAdapterModified<DiffItem> =
        AsyncListDifferDelegationAdapterModified(BaseDiffCallback(), adapterDelegatesManager)

    
}
package com.aya.digital.core.feature.choosers.multiselect.ui

import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter2
import com.aya.digital.core.ui.adapters.base.BaseDiffCallback
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.choosers.selectwithsearch.ui.SelectWithSearchItemDelegateListeners
import com.aya.digital.core.ui.delegates.features.choosers.selectwithsearch.ui.selectWithSearchItemDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

class SelectWithSearchAdapter(private val adapterListeners: MultiSelectChooserAdapterListeners) :
    BaseDelegateAdapter2() {

    val multiSelectDelegate = selectWithSearchItemDelegate(adapterListeners.multiSelectListener)

    private val adapterDelegatesManager =
        AdapterDelegatesManager<List<DiffItem>>().apply {
            addDelegate(multiSelectDelegate)
        }

    override val adapter: AsyncListDifferDelegationAdapterModified<DiffItem> =
        AsyncListDifferDelegationAdapterModified(
            BaseDiffCallback(),
            adapterDelegatesManager
        )


    companion object {

    }

}

class MultiSelectChooserAdapterListeners(
    val itemSelected: (id: Int) -> Unit

) {

    val multiSelectListener: SelectWithSearchItemDelegateListeners = SelectWithSearchItemDelegateListeners(itemSelected)

}
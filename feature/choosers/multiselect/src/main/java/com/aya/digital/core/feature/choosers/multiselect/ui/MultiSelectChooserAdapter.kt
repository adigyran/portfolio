package com.aya.digital.core.feature.choosers.multiselect.ui

import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter2
import com.aya.digital.core.ui.adapters.base.BaseDiffCallback
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.features.choosers.multiselect.ui.MultiSelectItemDelegateListeners
import com.aya.digital.core.ui.delegates.features.choosers.multiselect.ui.multiSelectItemDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

class MultiSelectChooserAdapter(private val adapterListeners: MultiSelectChooserAdapterListeners) :
    BaseDelegateAdapter2() {

    val multiSelectDelegate = multiSelectItemDelegate(adapterListeners.multiSelectListener)

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

    val multiSelectListener: MultiSelectItemDelegateListeners = MultiSelectItemDelegateListeners(itemSelected)

}
package com.aya.digital.feature.auth.chooser.ui

import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter2
import com.aya.digital.core.ui.adapters.base.BaseDiffCallback
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

class AuthChooserAdapter : BaseDelegateAdapter2() {

    private val adapterDelegatesManager =
        AdapterDelegatesManager<List<DiffItem>>().apply {
        }

    override val adapter: AsyncListDifferDelegationAdapterModified<DiffItem> =
        AsyncListDifferDelegationAdapterModified(
            BaseDiffCallback(),
            adapterDelegatesManager
        )
}
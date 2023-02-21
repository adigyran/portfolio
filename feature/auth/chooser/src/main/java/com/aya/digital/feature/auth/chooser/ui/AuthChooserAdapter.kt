package com.aya.digital.feature.auth.chooser.ui

import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter2
import com.aya.digital.core.ui.adapters.base.BaseDiffCallback
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.auth.chooser.buttons.ui.ButtonsDelegateListeners
import com.aya.digital.core.ui.delegates.auth.chooser.buttons.ui.chooserButtonsDelegate
import com.aya.digital.core.ui.delegates.auth.chooser.description.ui.DescriptionDelegateListeners
import com.aya.digital.core.ui.delegates.auth.chooser.description.ui.chooserDescriptionDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

class AuthChooserAdapter(private val adapterListeners: AuthChooserAdapterListeners) : BaseDelegateAdapter2() {

    val descriptionDelegate =  chooserDescriptionDelegate(adapterListeners.descriptionListeners)
    val buttonsDelegate =  chooserButtonsDelegate(adapterListeners.buttonsListeners)


    private val adapterDelegatesManager =
        AdapterDelegatesManager<List<DiffItem>>().apply {
            addDelegate(descriptionDelegate)
            addDelegate(buttonsDelegate)
        }

    override val adapter: AsyncListDifferDelegationAdapterModified<DiffItem> =
        AsyncListDifferDelegationAdapterModified(
            BaseDiffCallback(),
            adapterDelegatesManager
        )


    companion object {

    }

}

class AuthChooserAdapterListeners(val descriptionListeners: DescriptionDelegateListeners, val buttonsListeners: ButtonsDelegateListeners)
package com.aya.digital.core.feature.auth.signin.ui

import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter2
import com.aya.digital.core.ui.adapters.base.BaseDiffCallback
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.auth.signin.fields.ui.SignInFieldsDelegateListener
import com.aya.digital.core.ui.delegates.auth.signin.fields.ui.signInFieldsDelegate
import com.aya.digital.core.ui.delegates.fields.emailphone.ui.*
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

class SignInAdapter(private val adapterListeners: SignInAdapterListeners) : BaseDelegateAdapter2() {


    val fieldsDelegate = signInFieldsDelegate(
        RecyclerView.RecycledViewPool(),
        { recyclerScrollPositions },
        adapterListeners.signInFields
    )
    val buttonDelegate = filledButtonDelegate(adapterListeners.buttonListeners)
    private val adapterDelegatesManager =
        AdapterDelegatesManager<List<DiffItem>>().apply {
            addDelegate(buttonDelegate)
            addDelegate(fieldsDelegate)
        }

    override val adapter: AsyncListDifferDelegationAdapterModified<DiffItem> =
        AsyncListDifferDelegationAdapterModified(
            BaseDiffCallback(),
            adapterDelegatesManager
        )


    companion object {

    }

}

class SignInAdapterListeners(
    val buttonListeners: FilledButtonDelegateListeners,
    val signInFields: SignInFieldsDelegateListener,
)
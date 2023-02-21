package com.aya.digital.feature.auth.signup.ui

import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter2
import com.aya.digital.core.ui.adapters.base.BaseDiffCallback
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.fields.emailphone.ui.FilledButtonDelegateListeners
import com.aya.digital.core.ui.delegates.fields.emailphone.ui.SignInFieldsDelegateListener
import com.aya.digital.core.ui.delegates.fields.emailphone.ui.filledButtonDelegate
import com.aya.digital.core.ui.delegates.fields.emailphone.ui.signUpFieldsDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

class SignUpAdapter(private val adapterListeners: SignUpAdapterListeners) : BaseDelegateAdapter2() {

    val fieldsDelegate = signUpFieldsDelegate(
        RecyclerView.RecycledViewPool(),
        { recyclerScrollPositions },
        adapterListeners.signUpFields
    )

    val buttonDelegate = filledButtonDelegate(adapterListeners.buttonListeners)
    private val adapterDelegatesManager =
        AdapterDelegatesManager<List<DiffItem>>().apply {
            addDelegate(fieldsDelegate)
            addDelegate(buttonDelegate)
        }

    override val adapter: AsyncListDifferDelegationAdapterModified<DiffItem> =
        AsyncListDifferDelegationAdapterModified(
            BaseDiffCallback(),
            adapterDelegatesManager
        )


    companion object {

    }

}

class SignUpAdapterListeners(
    val buttonListeners: FilledButtonDelegateListeners,
    val signUpFields: SignInFieldsDelegateListener
)
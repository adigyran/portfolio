package com.aya.digital.feature.auth.signup.ui

import android.view.View
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter2
import com.aya.digital.core.ui.adapters.base.BaseDiffCallback
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.buttons.filledbutton.ui.FilledButtonDelegateListeners
import com.aya.digital.core.ui.delegates.components.buttons.filledbutton.ui.filledButtonDelegate
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.EmailPhoneDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.emailPhoneFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.name.model.ui.NameFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.name.model.ui.nameFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.password.ui.PasswordFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.password.ui.passwordFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.selection.ui.SelectionFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.selection.ui.selectionFieldDelegate
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.HeadlineLabelDelegateListeners
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.headlineLabelDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

class SignUpAdapter(private val adapterListeners: SignUpAdapterListeners) : BaseDelegateAdapter2() {

    private val emailPhoneDelegate = emailPhoneFieldDelegate(adapterListeners.emailPhoneField)
    private val passwordDelegate = passwordFieldDelegate(adapterListeners.passwordField)
    private val nameDelegate = nameFieldDelegate(adapterListeners.nameField)
    private val selectionDelegate = selectionFieldDelegate(adapterListeners.selectionField)
    private val headlineLabelDelegate = headlineLabelDelegate(adapterListeners.headlineLabel)

    private val adapterDelegatesManager =
        AdapterDelegatesManager<List<DiffItem>>().apply {
            addDelegate(emailPhoneDelegate)
            addDelegate(passwordDelegate)
            addDelegate(nameDelegate)
            addDelegate(selectionDelegate)
            addDelegate(headlineLabelDelegate)
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
    val emailPhoneFieldChangeListener: ((String) -> Unit),
    val passwordFieldChangeListener: ((tag: Int,text:String) -> Unit),
    val nameFieldChangeListener: ((tag: Int,text:String) -> Unit),
    val selectionClickListener: ((Int) -> Unit)

)
{
    val emailPhoneField: EmailPhoneDelegateListeners =
        EmailPhoneDelegateListeners(emailPhoneFieldChangeListener)
    val passwordField: PasswordFieldDelegateListeners =
        PasswordFieldDelegateListeners(passwordFieldChangeListener)
    val nameField: NameFieldDelegateListeners =
        NameFieldDelegateListeners(nameFieldChangeListener)
    val selectionField: SelectionFieldDelegateListeners =
        SelectionFieldDelegateListeners(selectionClickListener)
    val headlineLabel: HeadlineLabelDelegateListeners =
        HeadlineLabelDelegateListeners()
}
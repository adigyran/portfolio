package com.aya.digital.core.feature.auth.signin.ui

import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter2
import com.aya.digital.core.ui.adapters.base.BaseDiffCallback
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.buttons.filledbutton.ui.FilledButtonDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.EmailPhoneDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.emailPhoneFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.password.ui.PasswordFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.password.ui.passwordFieldDelegate
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.HeadlineLabelDelegateListeners
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.SpannableHelperLabelDelegateListeners
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.headlineLabelDelegate
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.spannableHelperLabelDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

class SignInAdapter(private val adapterListeners: SignInAdapterListeners) : BaseDelegateAdapter2() {

    val emailPhoneDelegate = emailPhoneFieldDelegate(adapterListeners.emailPhoneField)
    val passwordDelegate = passwordFieldDelegate(adapterListeners.passwordField)
    val headlineLabelDelegate = headlineLabelDelegate(adapterListeners.headlineLabel)
    val spannableHelperDelegate = spannableHelperLabelDelegate(adapterListeners.spannableLabel)

    private val adapterDelegatesManager =
        AdapterDelegatesManager<List<DiffItem>>().apply {
            addDelegate(emailPhoneDelegate)
            addDelegate(passwordDelegate)
            addDelegate(headlineLabelDelegate)
            addDelegate(spannableHelperDelegate)
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
    val emailPhoneChanged: ((String) -> Unit),
    val passwordChanged: ((tag: Int,text:String) -> Unit),
    )
{
    val emailPhoneField: EmailPhoneDelegateListeners =
        EmailPhoneDelegateListeners(emailPhoneChanged)
    val passwordField: PasswordFieldDelegateListeners =
        PasswordFieldDelegateListeners(passwordChanged)
    val headlineLabel: HeadlineLabelDelegateListeners =
        HeadlineLabelDelegateListeners()
    val spannableLabel: SpannableHelperLabelDelegateListeners =
        SpannableHelperLabelDelegateListeners()

}
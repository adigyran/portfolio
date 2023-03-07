package com.aya.digital.core.feature.auth.signin.ui

import android.os.Bundle
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.feature.auth.signin.di.restorePasswordDiModule
import com.aya.digital.core.feature.auth.signin.ui.model.RestorePasswordStateTransformer
import com.aya.digital.core.feature.auth.signin.ui.model.RestorePasswordUiModel
import com.aya.digital.core.feature.auth.signin.viewmodel.RestorePasswordState
import com.aya.digital.core.feature.auth.signin.viewmodel.RestorePasswordViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.ext.SpannableObject
import com.aya.digital.core.ext.colors
import com.aya.digital.core.feature.auth.signin.restorepassword.databinding.ViewRestorePasswordBinding
import com.aya.digital.core.ui.base.ext.createSpannableText
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.EmailPhoneDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.emailPhoneFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.password.ui.PasswordFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.password.ui.passwordFieldDelegate
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.HeadlineLabelDelegateListeners
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.SpannableHelperLabelDelegateListeners
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.headlineLabelDelegate
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.spannableHelperLabelDelegate
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class RestorePasswordView :
    DiFragment<ViewRestorePasswordBinding, RestorePasswordViewModel, RestorePasswordState, BaseSideEffect, RestorePasswordUiModel, RestorePasswordStateTransformer>() {

    private val viewModelFactory: ((Unit) -> RestorePasswordViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> RestorePasswordStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate { headlineLabelDelegate(HeadlineLabelDelegateListeners()) }
            delegate { emailPhoneFieldDelegate(EmailPhoneDelegateListeners {  }) }
            delegate { passwordFieldDelegate(PasswordFieldDelegateListeners{tag, text ->  }) }
        }
    }

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
        if(savedInstanceState==null) {
            recyclers.add(binding.recycler)
            with(binding.recycler) {
                itemAnimator = null
                setHasFixedSize(true)
                setItemViewCacheSize(30)
                isNestedScrollingEnabled = false

                val lm = LinearLayoutManager(
                    context,
                    RecyclerView.VERTICAL,
                    false
                )

                layoutManager = lm

            }
        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)

    }

    override fun provideDiModule(): DI.Module = restorePasswordDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewRestorePasswordBinding = ViewRestorePasswordBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: RestorePasswordState) {
        stateTransformer(state).data?.let {
            adapter.items = it
            if (binding.recycler.adapter == null) {
                binding.recycler.swapAdapter(adapter, true)
            }
        }
    }

    override fun provideViewModel(): RestorePasswordViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): RestorePasswordStateTransformer =
        stateTransformerFactory(Unit)

}

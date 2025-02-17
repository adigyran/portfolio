package com.aya.digital.core.feature.auth.restorepassword.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.argument
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.feature.auth.restorepassword.di.restorePasswordDiModule
import com.aya.digital.core.feature.auth.restorepassword.ui.model.RestorePasswordStateTransformer
import com.aya.digital.core.feature.auth.restorepassword.ui.model.RestorePasswordUiModel
import com.aya.digital.core.feature.auth.restorepassword.viewmodel.RestorePasswordState
import com.aya.digital.core.feature.auth.restorepassword.viewmodel.RestorePasswordViewModel
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ext.createFragment
import com.aya.digital.core.ext.toggleAvailability
import com.aya.digital.core.feature.auth.restorepassword.navigation.RestorePasswordOperationStateParam
import com.aya.digital.core.feature.auth.restorepassword.viewmodel.RestorePasswordSideEffects
import com.aya.digital.core.feature.auth.signin.restorepassword.databinding.ViewRestorePasswordBinding
import com.aya.digital.core.localisation.R
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.base.validation.MailValidator
import com.aya.digital.core.ui.base.validation.NotEmptyValidator
import com.aya.digital.core.ui.base.validation.PasswordRepeatValidator
import com.aya.digital.core.ui.base.validation.PasswordValidator
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.EmailDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.EmailFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.PhoneFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.password.ui.PasswordFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.password.ui.PasswordFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.HeadlineLabelDelegate
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.HeadlineLabelDelegateListeners
import kotlinx.parcelize.Parcelize
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

internal class RestorePasswordView :
    DiFragment<ViewRestorePasswordBinding, RestorePasswordViewModel, RestorePasswordState, RestorePasswordSideEffects, RestorePasswordUiModel, RestorePasswordStateTransformer>() {

    private var param: Param by argument("param")

    private val viewModelFactory: ((Unit) -> RestorePasswordViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> RestorePasswordStateTransformer) by kodein.on(
        context = this
    ).factory()


    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate { HeadlineLabelDelegate(HeadlineLabelDelegateListeners()) }
            delegate { EmailFieldDelegate(EmailDelegateListeners(viewModel::emailFieldChanging)) }
            delegate { PasswordFieldDelegate(PasswordFieldDelegateListeners(viewModel::passwordFieldsChanging)) }
        }
    }

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
        recyclers.add(binding.recycler)
        binding.saveBtn bindClick { viewModel.saveButtonClicked() }
        binding.toolbarLogo.backButton bindClick { viewModel.onBack() }
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
            addItemDecoration(RestorePasswordDecoration())
        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)

    }

    override fun provideDiModule(): DI.Module =
        restorePasswordDiModule(tryTyGetParentRouter(), param)

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewRestorePasswordBinding = ViewRestorePasswordBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: RestorePasswordSideEffects) =
        when (sideEffect) {
            is RestorePasswordSideEffects.Error -> {
                processErrorSideEffect(sideEffect.error)
            }
        }

    override fun render(state: RestorePasswordState) {
        stateTransformer(state).run {
            data?.let {
                adapter.items = it
                if (binding.recycler.adapter == null) {
                    binding.recycler.swapAdapter(adapter, true)
                }
            }
            buttonText.let { binding.saveBtn.text = it }
            buttonEnabled.let { enabled ->
                binding.saveBtn.toggleAvailability(enabled)
            }
        }
    }

    override fun provideViewModel(): RestorePasswordViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): RestorePasswordStateTransformer =
        stateTransformerFactory(Unit)


    @Parcelize
    class Param(
        val requestCode: String,
        val operationState: RestorePasswordOperationStateParam
    ) : Parcelable {
    }

    companion object {
        fun getNewInstance(
            requestCode: String,
            operationState: RestorePasswordOperationStateParam
        ): RestorePasswordView = createFragment(
            Param(requestCode, operationState)
        )
    }

}

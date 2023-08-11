package com.aya.digital.core.feature.profile.security.changepassword.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.argument
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.createFragment
import com.aya.digital.core.feature.profile.security.changepassword.databinding.ViewProfileChangePasswordBinding
import com.aya.digital.core.feature.profile.security.changepassword.di.profileSecurityChangePasswordDiModule
import com.aya.digital.core.feature.profile.security.changepassword.ui.model.ProfileSecurityChangePasswordStateTransformer
import com.aya.digital.core.feature.profile.security.changepassword.ui.model.ProfileSecurityChangePasswordUiModel
import com.aya.digital.core.feature.profile.security.changepassword.viewmodel.ProfileSecurityChangePasswordSideEffects
import com.aya.digital.core.feature.profile.security.changepassword.viewmodel.ProfileSecurityChangePasswordState
import com.aya.digital.core.feature.profile.security.changepassword.viewmodel.ProfileSecurityChangePasswordViewModel
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.delegates.components.fields.password.ui.PasswordFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.password.ui.PasswordFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.HeadlineTwoLineLabelDelegate
import kotlinx.parcelize.Parcelize
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

internal class ProfileSecurityChangePasswordView :
    DiFragment<ViewProfileChangePasswordBinding, ProfileSecurityChangePasswordViewModel, ProfileSecurityChangePasswordState, ProfileSecurityChangePasswordSideEffects, ProfileSecurityChangePasswordUiModel, ProfileSecurityChangePasswordStateTransformer>() {

    private var param: Param by argument("param")

    private val viewModelFactory: ((Unit) -> ProfileSecurityChangePasswordViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileSecurityChangePasswordStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate { HeadlineTwoLineLabelDelegate() }
            delegate { PasswordFieldDelegate(PasswordFieldDelegateListeners(viewModel::passwordFieldChanged)) }
        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        binding.toolbar.backButton bindClick {viewModel.onBack()}
        binding.saveBtn bindClick {viewModel.onChangePassword()}
        binding.toolbar.title.text = "Change password"
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
            addItemDecoration(ProfileSecurityChangePasswordDecoration())
        }
    }

    override fun provideDiModule(): DI.Module =
        profileSecurityChangePasswordDiModule(tryTyGetParentRouter(), param)

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileChangePasswordBinding =
        ViewProfileChangePasswordBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: ProfileSecurityChangePasswordSideEffects) =
        when(sideEffect)
        {
            is ProfileSecurityChangePasswordSideEffects.Error -> processErrorSideEffect(sideEffect.error)
        }

    override fun render(state: ProfileSecurityChangePasswordState) {
        stateTransformer(state).data?.let {
            adapter.items = it
            if (binding.recycler.adapter == null) {
                binding.recycler.swapAdapter(adapter, true)
            }
        }
    }

    @Parcelize
    class Param(
        val requestCode: String
    ) : Parcelable

    override fun provideViewModel(): ProfileSecurityChangePasswordViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfileSecurityChangePasswordStateTransformer =
        stateTransformerFactory(Unit)

    companion object {
        fun getNewInstance(requestCode: String): ProfileSecurityChangePasswordView = createFragment(
            Param(requestCode)
        )
    }
}

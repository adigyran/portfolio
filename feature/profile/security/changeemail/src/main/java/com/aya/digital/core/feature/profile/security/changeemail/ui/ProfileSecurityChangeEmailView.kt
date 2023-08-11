package com.aya.digital.core.feature.profile.security.changeemail.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.argument
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.createFragment
import com.aya.digital.core.ext.toggleAvailability
import com.aya.digital.core.feature.profile.security.changeemail.databinding.ViewProfileSecurityChangeEmailBinding
import com.aya.digital.core.feature.profile.security.changeemail.di.profileSecurityChangeEmailDiModule
import com.aya.digital.core.feature.profile.security.changeemail.ui.model.ProfileSecurityChangeEmailStateTransformer
import com.aya.digital.core.feature.profile.security.changeemail.ui.model.ProfileSecurityChangeEmailUiModel
import com.aya.digital.core.feature.profile.security.changeemail.viewmodel.ProfileSecurityChangeEmailSideEffects
import com.aya.digital.core.feature.profile.security.changeemail.viewmodel.ProfileSecurityChangeEmailState
import com.aya.digital.core.feature.profile.security.changeemail.viewmodel.ProfileSecurityChangeEmailViewModel
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.EmailDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.EmailFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.PhoneFieldDelegate
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.HeadlineTwoLineLabelDelegate
import kotlinx.parcelize.Parcelize
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

internal class ProfileSecurityChangeEmailView :
    DiFragment<ViewProfileSecurityChangeEmailBinding, ProfileSecurityChangeEmailViewModel, ProfileSecurityChangeEmailState, ProfileSecurityChangeEmailSideEffects, ProfileSecurityChangeEmailUiModel, ProfileSecurityChangeEmailStateTransformer>() {

    private var param: Param by argument("param")


    private val viewModelFactory: ((Unit) -> ProfileSecurityChangeEmailViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileSecurityChangeEmailStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate { HeadlineTwoLineLabelDelegate() }
            delegate { EmailFieldDelegate(EmailDelegateListeners(inputFieldChangeListener = viewModel::emailChanged)) }
        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        binding.toolbar.backButton bindClick {viewModel.onBack()}
        binding.saveBtn bindClick {viewModel.saveClicked()}
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
            addItemDecoration(ProfileSecurityChangeEmailDecoration())
        }
    }


    override fun provideDiModule(): DI.Module =
        profileSecurityChangeEmailDiModule(tryTyGetParentRouter(), param)

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileSecurityChangeEmailBinding =
        ViewProfileSecurityChangeEmailBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: ProfileSecurityChangeEmailSideEffects) =
        when(sideEffect)
        {
            is ProfileSecurityChangeEmailSideEffects.Error -> processErrorSideEffect(sideEffect.error)
        }

    override fun render(state: ProfileSecurityChangeEmailState) {
        stateTransformer(state).run {

            data?.let {
                adapter.items = it
                if (binding.recycler.adapter == null) {
                    binding.recycler.swapAdapter(adapter, true)
                }
            }
            buttonEnabled.run {
                binding.saveBtn.toggleAvailability(this)
            }
        }
    }

    override fun provideViewModel(): ProfileSecurityChangeEmailViewModel =
        viewModelFactory(Unit)

    override fun provideStateTransformer(): ProfileSecurityChangeEmailStateTransformer =
        stateTransformerFactory(Unit)

    @Parcelize
    class Param(
        val requestCode: String
    ) : Parcelable

    companion object {
        fun getNewInstance(requestCode: String): ProfileSecurityChangeEmailView =
            createFragment(
                Param(requestCode)
            )
    }

}

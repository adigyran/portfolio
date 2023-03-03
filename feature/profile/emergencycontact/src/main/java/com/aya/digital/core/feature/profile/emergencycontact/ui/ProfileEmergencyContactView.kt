package com.aya.digital.core.feature.profile.emergencycontact.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.feature.profile.emergencycontact.databinding.ViewProfileEmergencyContactBinding
import com.aya.digital.core.feature.profile.emergencycontact.di.profileEmergencyContactDiModule
import com.aya.digital.core.feature.profile.emergencycontact.ui.model.ProfileEmergencyContactStateTransformer
import com.aya.digital.core.feature.profile.emergencycontact.ui.model.ProfileEmergencyContactUiModel
import com.aya.digital.core.feature.profile.emergencycontact.viewmodel.ProfileEmergencyContactState
import com.aya.digital.core.feature.profile.emergencycontact.viewmodel.ProfileEmergencyContactViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.delegates.components.fields.name.model.ui.NameFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.name.model.ui.nameFieldDelegate
import com.aya.digital.core.ui.delegates.profile.info.ui.emergencyContactInfoDelegate
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class ProfileEmergencyContactView :
    DiFragment<ViewProfileEmergencyContactBinding, ProfileEmergencyContactViewModel, ProfileEmergencyContactState, BaseSideEffect, ProfileEmergencyContactUiModel, ProfileEmergencyContactStateTransformer>() {

    private val viewModelFactory: ((Unit) -> ProfileEmergencyContactViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileEmergencyContactStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate { nameFieldDelegate(NameFieldDelegateListeners { tag, text -> }) }
            delegate { emergencyContactInfoDelegate() }
        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        binding.editSaveBtn bindClick { viewModel::buttonClicked }
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


    override fun provideDiModule(): DI.Module =
        profileEmergencyContactDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileEmergencyContactBinding =
        ViewProfileEmergencyContactBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: ProfileEmergencyContactState) {
        stateTransformer(state).run {
            this.data?.let {
                adapter.items = it
                if (binding.recycler.adapter == null) {
                    binding.recycler.swapAdapter(adapter, true)
                }
            }
            this.buttonText.let {
                binding.editSaveBtn.text = it
            }
        }
    }

    override fun provideViewModel(): ProfileEmergencyContactViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfileEmergencyContactStateTransformer =
        stateTransformerFactory(Unit)

}

package com.aya.digital.core.feature.profile.generalinfo.edit.ui

import android.os.Bundle
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.feature.profile.generalinfo.edit.databinding.ViewProfileGeneralinfoEditBinding
import com.aya.digital.core.feature.profile.generalinfo.edit.di.profileGeneralInfoEditDiModule
import com.aya.digital.core.feature.profile.generalinfo.edit.ui.model.ProfileGeneralInfoEditStateTransformer
import com.aya.digital.core.feature.profile.generalinfo.edit.ui.model.ProfileGeneralInfoEditUiModel
import com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel.ProfileGeneralInfoEditState
import com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel.ProfileGeneralInfoEditViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.ext.SpannableObject
import com.aya.digital.core.ui.base.ext.colors
import com.aya.digital.core.ui.base.ext.createSpannableText
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
import com.aya.digital.core.ui.delegates.components.fields.name.model.ui.NameFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.name.model.ui.nameFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.selection.ui.DropDownFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.selection.ui.SelectionFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.selection.ui.dropDownFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.selection.ui.selectionFieldDelegate
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on
import timber.log.Timber

class ProfileGeneralInfoEditView :
    DiFragment<ViewProfileGeneralinfoEditBinding, ProfileGeneralInfoEditViewModel, ProfileGeneralInfoEditState, BaseSideEffect, ProfileGeneralInfoEditUiModel, ProfileGeneralInfoEditStateTransformer>() {

    private val viewModelFactory: ((Unit) -> ProfileGeneralInfoEditViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileGeneralInfoEditStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate { nameFieldDelegate(NameFieldDelegateListeners { tag, text -> Timber.d("$tag $text") }) }
            delegate { selectionFieldDelegate(SelectionFieldDelegateListeners { tag -> Timber.d("$tag") }) }
            delegate { dropDownFieldDelegate(DropDownFieldDelegateListeners { selectedItem -> Timber.d(
                selectedItem
            ) }) }
        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        binding.saveBtn bindClick {viewModel.onSaveProfileClicked()}
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



    override fun provideDiModule(): DI.Module = profileGeneralInfoEditDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileGeneralinfoEditBinding = ViewProfileGeneralinfoEditBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: ProfileGeneralInfoEditState) {
        stateTransformer(state).data?.let {
            adapter.items = it
            if (binding.recycler.adapter == null) {
                binding.recycler.swapAdapter(adapter, true)
            }
        }
    }

    override fun provideViewModel(): ProfileGeneralInfoEditViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfileGeneralInfoEditStateTransformer =
        stateTransformerFactory(Unit)

}

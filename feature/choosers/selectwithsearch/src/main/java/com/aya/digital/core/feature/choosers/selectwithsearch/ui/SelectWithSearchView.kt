package com.aya.digital.core.feature.choosers.multiselect.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.argument
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.createFragment
import com.aya.digital.core.feature.choosers.multiselect.di.selectWithSearchDiModule
import com.aya.digital.core.feature.choosers.multiselect.ui.model.SelectWithSearchStateTransformer
import com.aya.digital.core.feature.choosers.multiselect.ui.model.SelectWithSearchChooserUiModel
import com.aya.digital.core.feature.choosers.multiselect.viewmodel.SelectWithSearchChooserState
import com.aya.digital.core.feature.choosers.multiselect.viewmodel.SelectWithSearchChooserViewModel
import com.aya.digital.core.feature.choosers.selectwithsearch.databinding.ViewSelectWithSearchBinding
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.base.screens.DiFragment
import kotlinx.parcelize.Parcelize
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class SelectWithSearchView :
    DiFragment<ViewSelectWithSearchBinding, SelectWithSearchChooserViewModel, SelectWithSearchChooserState, BaseSideEffect, SelectWithSearchChooserUiModel, SelectWithSearchStateTransformer>() {

    private var param: Param by argument("param")


    private val viewModelFactory: ((Unit) -> SelectWithSearchChooserViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> SelectWithSearchStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        SelectWithSearchAdapter(
            MultiSelectChooserAdapterListeners(viewModel::selectItem)
        )
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        recyclers.add(binding.recycler)

        binding.applyBtn bindClick { viewModel.applyFilters() }

        with(binding.recycler) {
            itemAnimator = null
            setHasFixedSize(false)
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
        selectWithSearchDiModule(tryTyGetParentRouter(), param)

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewSelectWithSearchBinding = ViewSelectWithSearchBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) {
        super.sideEffect(sideEffect)
    }

    override fun render(state: SelectWithSearchChooserState) {
        stateTransformer(state).data?.let {
            adapter.items = it
            if (binding.recycler.adapter == null) {
                binding.recycler.swapAdapter(adapter, true)
            }
        }
    }

    override fun provideViewModel(): SelectWithSearchChooserViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): SelectWithSearchStateTransformer =
        stateTransformerFactory(Unit)


    @Parcelize
    class Param(
        val requestCode: String,
        val isMultiChoose: Boolean,
        val selectedItems: Set<Int>?
    ) : Parcelable

    companion object {
        fun getNewInstance(
            requestCode: String,
            isMultiChoose: Boolean,
            selectedItems: Set<Int>?
        ): SelectWithSearchView = createFragment(Param(requestCode, isMultiChoose, selectedItems))
    }

}

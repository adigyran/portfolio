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
import com.aya.digital.core.feature.choosers.multiselect.databinding.ViewMultiselectChooserBinding
import com.aya.digital.core.feature.choosers.multiselect.di.multiSelectChooserDiModule
import com.aya.digital.core.feature.choosers.multiselect.ui.model.MultiSelectChooserStateTransformer
import com.aya.digital.core.feature.choosers.multiselect.ui.model.MultiSelectChooserUiModel
import com.aya.digital.core.feature.choosers.multiselect.viewmodel.MultiSelectChooserState
import com.aya.digital.core.feature.choosers.multiselect.viewmodel.MultiSelectChooserViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.base.screens.DiFragment
import kotlinx.parcelize.Parcelize
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class MultiSelectChooserView :
    DiFragment<ViewMultiselectChooserBinding, MultiSelectChooserViewModel, MultiSelectChooserState, BaseSideEffect, MultiSelectChooserUiModel, MultiSelectChooserStateTransformer>() {

    private var param: Param by argument("param")


    private val viewModelFactory: ((Unit) -> MultiSelectChooserViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> MultiSelectChooserStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        MultiSelectChooserAdapter(
            MultiSelectChooserAdapterListeners(viewModel::selectItem)
        )
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        recyclers.add(binding.recycler)

        binding.applyBtn bindClick {viewModel.applyFilters()}

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

    override fun provideDiModule(): DI.Module = multiSelectChooserDiModule(tryTyGetParentRouter(),param)

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewMultiselectChooserBinding = ViewMultiselectChooserBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: MultiSelectChooserState) {
        stateTransformer(state).data?.let {
            adapter.items = it
            if (binding.recycler.adapter == null) {
                binding.recycler.swapAdapter(adapter, true)
            }
        }
    }

    override fun provideViewModel(): MultiSelectChooserViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): MultiSelectChooserStateTransformer =
        stateTransformerFactory(Unit)


    @Parcelize
    class Param(
        val requestCode: String,
        val selectedItems: Set<Int>?
    ) : Parcelable
    companion object {
        fun getNewInstance(requestCode: String, selectedItems: Set<Int>?): MultiSelectChooserView  = createFragment(Param(requestCode,selectedItems))
    }

}

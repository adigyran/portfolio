package com.aya.digital.core.feature.choosers.selectwithsearch.ui

import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.argument
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.createFragment
import com.aya.digital.core.feature.choosers.selectwithsearch.di.selectWithSearchDiModule
import com.aya.digital.core.feature.choosers.selectwithsearch.ui.model.SelectWithSearchStateTransformer
import com.aya.digital.core.feature.choosers.selectwithsearch.ui.model.SelectWithSearchChooserUiModel
import com.aya.digital.core.feature.choosers.selectwithsearch.viewmodel.SelectWithSearchChooserState
import com.aya.digital.core.feature.choosers.selectwithsearch.viewmodel.SelectWithSearchChooserViewModel
import com.aya.digital.core.feature.choosers.selectwithsearch.databinding.ViewSelectWithSearchBinding
import com.aya.digital.core.feature.choosers.selectwithsearch.viewmodel.SelectWithSearchChooserSideEffects
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.base.utils.EndlessRecyclerViewScrollListener
import com.aya.digital.core.ui.delegates.features.choosers.selectwithsearch.ui.SelectWithSearchItemDelegate
import kotlinx.parcelize.Parcelize
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class SelectWithSearchView :
    DiFragment<ViewSelectWithSearchBinding, SelectWithSearchChooserViewModel, SelectWithSearchChooserState, SelectWithSearchChooserSideEffects, SelectWithSearchChooserUiModel, SelectWithSearchStateTransformer>() {

    private var param: Param by argument("param")


    private val viewModelFactory: ((Unit) -> SelectWithSearchChooserViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> SelectWithSearchStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate {
                SelectWithSearchItemDelegate(
                    viewModel::selectItem
                )
            }
        }
    }

    private val textWatcher = object : TextWatcher {
        var firstCall = true
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (firstCall) {
                firstCall = false
                return
            }
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

        override fun afterTextChanged(s: Editable) = viewModel.onSearchTextChanged(s.toString())
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        recyclers.add(binding.recycler)

        binding.applyBtn bindClick { viewModel.applyFilters() }
        binding.toolbar.backButton bindClick { viewModel.onBack() }

        with(binding.recycler) {
            itemAnimator = null
            setHasFixedSize(true)
            isNestedScrollingEnabled = false

            val lm = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )

            layoutManager = lm
            addItemDecoration(SelectWithSearchDecoration())
            val scrollListener = object : EndlessRecyclerViewScrollListener(lm) {
                override fun onLoadMore() {
                    viewModel.loadNextPage()
                }
            }
            addOnScrollListener(scrollListener)
        }
    }

    override fun provideDiModule(): DI.Module =
        selectWithSearchDiModule(tryTyGetParentRouter(), param)

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewSelectWithSearchBinding = ViewSelectWithSearchBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: SelectWithSearchChooserSideEffects) =
        when (sideEffect) {
            is SelectWithSearchChooserSideEffects.Error -> {
                processErrorSideEffect(sideEffect.error)
            }
        }

    override fun render(state: SelectWithSearchChooserState) {
        stateTransformer(state).run {
            data?.let {
                adapter.items = it
                if (binding.recycler.adapter == null) {
                    binding.recycler.swapAdapter(adapter, true)
                }
            }
            searchText.run {
                binding.toolbar.edField.removeTextChangedListener(textWatcher)
                if (binding.toolbar.edField.text.toString() != searchText) {
                    binding.toolbar.edField.setText(searchText)
                }
                binding.toolbar.edField.addTextChangedListener(textWatcher)
            }
            title.run { binding.toolbar.title.text = title }
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

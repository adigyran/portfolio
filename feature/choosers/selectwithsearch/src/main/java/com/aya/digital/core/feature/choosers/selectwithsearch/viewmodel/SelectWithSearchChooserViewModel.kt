package com.aya.digital.core.feature.choosers.multiselect.viewmodel

import com.aya.digital.core.data.base.dataprocessing.dataloading.DataLoadingOperationWithPagination
import com.aya.digital.core.data.base.dataprocessing.dataloading.enums.OperationState
import com.aya.digital.core.data.base.result.models.dictionaries.MultiSelectResultModel
import com.aya.digital.core.data.base.result.models.dictionaries.SelectedItem
import com.aya.digital.core.domain.dictionaries.base.GetMultiSelectItemsUseCase
import com.aya.digital.core.feature.choosers.multiselect.model.SelectionItem
import com.aya.digital.core.feature.choosers.multiselect.navigation.SelectWithSearchNavigationEvents
import com.aya.digital.core.feature.choosers.multiselect.ui.SelectWithSearchView
import com.aya.digital.core.feature.choosers.selectwithsearch.viewmodel.SelectWithSearchChooserSideEffects
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.reactive.asFlow
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SelectWithSearchChooserViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val getMultiSelectItemsUseCase: GetMultiSelectItemsUseCase,
    private val param: SelectWithSearchView.Param
) :
    BaseViewModel<SelectWithSearchChooserState, SelectWithSearchChooserSideEffects>() {
    override val container =
        container<SelectWithSearchChooserState, SelectWithSearchChooserSideEffects>(
            initialState = SelectWithSearchChooserState(dataOperation = DataLoadingOperationWithPagination.Idle),
        )
        {
            if (it.items.isEmpty()) {
                loadItems(null)
            }
            if (!param.selectedItems.isNullOrEmpty()) {
                preselectItems(param.selectedItems)
            }
        }

    private fun preselectItems(selectedItems: Set<Int>) = intent {

        reduce { state.copy(selectedItems = selectedItems.map { SelectionItem(it, "") }.toSet()) }
    }

    fun onSearchTextChanged(text: String) = intent {
        reduce { state.copy(searchTerm = text) }
        loadItems(state.searchTerm)
    }


    private fun getItems(state: SelectWithSearchChooserState) = getMultiSelectItemsUseCase(
        searchTerm = state.searchTerm,
        cursor = state.cursor,
        type = param.requestCode
    ).asFlow()

    private fun loadItems(searchTerm: String?) = intent(registerIdling = false) {
        if (state.dataOperation.isLoading || state.dataOperation.isNextPageLoading) return@intent
        reduce {
            state.copy(
                cursor = null,
                items = listOf(),
                searchTerm = searchTerm,
                dataOperation = DataLoadingOperationWithPagination.LoadingData(OperationState.PROGRESS)
            )
        }
        getItems(state)
            .collect { resultModel ->
                resultModel.processResult({ itemsPagination ->
                val items = itemsPagination.items.map { SelectionItem(it.id, it.text) }
                reduce {
                    state.copy(
                        items = items,
                        dataOperation = DataLoadingOperationWithPagination.Idle,
                        cursor = itemsPagination.cursor,
                    )
                }
            }, { processError(it) })

        }
    }
    fun loadNextPage() = intent {
        if (state.dataOperation.isLoading || state.dataOperation.isNextPageLoading) return@intent
        if (state.cursor.isNullOrBlank()) return@intent
        reduce {
            state.copy(dataOperation = DataLoadingOperationWithPagination.NextPageLoading(OperationState.PROGRESS))
        }
        getItems(state)
            .collect { resultModel ->
                resultModel.processResult({ itemsPagination ->
                    reduce {
                        val items = addItems(state.items, itemsPagination.items)
                        state.copy(
                            items = items,
                            cursor = itemsPagination.cursor,
                            dataOperation = (DataLoadingOperationWithPagination.Idle)
                        )
                    }
                }, { processError(it) })
            }
    }

    private fun addItems(oldItems: List<SelectionItem>?, newItems: List<SelectionItem>) =
        mutableListOf<SelectionItem>()
            .apply {
                oldItems?.run { addAll(this) }
                newItems.run { addAll(this) }
            }


    fun selectItem(itemId: Int) = intent {
        val selectedItems = when (param.isMultiChoose) {
            true -> {
                val selectedItemsTemp = state.selectedItems.toMutableSet()
                if (!selectedItemsTemp.removeIf { it.id == itemId }) selectedItemsTemp.add(state.items.first { it.id == itemId })
                selectedItemsTemp.toSet()
            }

            false -> {
                val selectedItemsTemp = state.selectedItems.toMutableSet()
                selectedItemsTemp.clear()
                selectedItemsTemp.add(state.items.first { it.id == itemId })
                selectedItemsTemp.toSet()
            }
        }

        reduce { state.copy(selectedItems = selectedItems.toSet()) }
    }

    fun applyFilters() = intent {
        coordinatorRouter.sendEvent(
            SelectWithSearchNavigationEvents.FinishWithResult(
                param.requestCode,
                MultiSelectResultModel(state.selectedItems.map { SelectedItem(it.id, it.text) }
                    .toSet())
            )
        )
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(SelectWithSearchChooserSideEffects.Error(errorSideEffect))
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }
}
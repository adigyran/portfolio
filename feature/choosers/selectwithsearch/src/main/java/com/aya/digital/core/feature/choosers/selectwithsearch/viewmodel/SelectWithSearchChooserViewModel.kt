package com.aya.digital.core.feature.choosers.multiselect.viewmodel

import com.aya.digital.core.data.base.result.models.dictionaries.MultiSelectResultModel
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
    override val container = container<SelectWithSearchChooserState, SelectWithSearchChooserSideEffects>(
        initialState = SelectWithSearchChooserState(),
    )
    {
        if (it.items.isEmpty()) {
            loadItems("")
        }
        if (param.selectedItems != null && param.selectedItems.isNotEmpty()) {
            preselectItems(param.selectedItems)
        }
    }

    private fun preselectItems(selectedItems: Set<Int>) = intent {

        reduce { state.copy(selectedItems = selectedItems) }
    }

    private fun loadItems(searchTerm: String) = intent(registerIdling = false) {
        getMultiSelectItemsUseCase(searchTerm, param.requestCode).asFlow().collect { result ->
            result.processResult({ items ->
                val selectedItems = items.map { SelectionItem(it.id, it.text) }
                reduce {
                    state.copy(items = selectedItems)
                }
            }, {processError(it)})

        }
    }

    fun selectItem(itemId: Int) = intent {
        val selectedItems = when(param.isMultiChoose)
        {
            true -> {
                val selectedItemsTemp = state.selectedItems.toMutableSet()
                if (selectedItemsTemp.contains(itemId)) selectedItemsTemp.remove(itemId)
                else selectedItemsTemp.add(itemId)
                selectedItemsTemp.toSet()
            }
            false -> {
                val selectedItemsTemp = state.selectedItems.toMutableSet()
                selectedItemsTemp.clear()
                selectedItemsTemp.add(itemId)
                selectedItemsTemp.toSet()
            }
        }

        reduce { state.copy(selectedItems = selectedItems.toSet()) }
    }

    fun applyFilters() = intent {
        coordinatorRouter.sendEvent(SelectWithSearchNavigationEvents.FinishWithResult(param.requestCode,
            MultiSelectResultModel(state.selectedItems)))
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(SelectWithSearchChooserSideEffects.Error(errorSideEffect))
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }
}
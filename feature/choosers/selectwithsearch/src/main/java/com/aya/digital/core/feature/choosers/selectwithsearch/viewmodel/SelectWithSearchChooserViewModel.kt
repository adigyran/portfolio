package com.aya.digital.core.feature.choosers.multiselect.viewmodel

import com.aya.digital.core.data.base.result.models.dictionaries.MultiSelectResultModel
import com.aya.digital.core.domain.dictionaries.GetMultiSelectItemsUseCase
import com.aya.digital.core.feature.choosers.multiselect.model.SelectionItem
import com.aya.digital.core.feature.choosers.multiselect.navigation.SelectWithSearchNavigationEvents
import com.aya.digital.core.feature.choosers.multiselect.ui.SelectWithSearchView
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.reactive.asFlow
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SelectWithSearchChooserViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val getMultiSelectItemsUseCase: GetMultiSelectItemsUseCase,
    private val param: SelectWithSearchView.Param
) :
    BaseViewModel<SelectWithSearchChooserState, BaseSideEffect>() {
    override val container = container<SelectWithSearchChooserState, BaseSideEffect>(
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
            }, {})

        }
    }

    fun selectItem(itemId: Int) = intent {
        val selectedItems = state.selectedItems.toMutableSet()
        if (selectedItems.contains(itemId)) selectedItems.remove(itemId)
        else selectedItems.add(itemId)
        reduce { state.copy(selectedItems = selectedItems.toSet()) }
    }

    fun applyFilters() = intent {
        coordinatorRouter.sendEvent(SelectWithSearchNavigationEvents.FinishWithResult(param.requestCode,
            MultiSelectResultModel(state.selectedItems)))
    }


}
package com.aya.digital.core.feature.choosers.multiselect.navigation

import com.aya.digital.core.data.base.result.models.dictionaries.MultiSelectResultModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class SelectWithSearchNavigationEvents : CoordinatorEvent() {
    data class FinishWithResult(val requestCode:String,val result: MultiSelectResultModel) : SelectWithSearchNavigationEvents()
}
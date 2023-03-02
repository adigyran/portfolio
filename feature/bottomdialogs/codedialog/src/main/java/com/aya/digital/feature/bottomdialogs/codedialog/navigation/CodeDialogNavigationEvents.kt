package com.aya.digital.feature.bottomdialogs.codedialog.navigation

import com.aya.digital.core.data.base.result.models.code.CodeResultModel
import com.aya.digital.core.data.base.result.models.dictionaries.MultiSelectResultModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class CodeDialogNavigationEvents : CoordinatorEvent() {
    data class FinishWithResult(val requestCode:String,val result: CodeResultModel) : CodeDialogNavigationEvents()
    object Exit : CodeDialogNavigationEvents()


}
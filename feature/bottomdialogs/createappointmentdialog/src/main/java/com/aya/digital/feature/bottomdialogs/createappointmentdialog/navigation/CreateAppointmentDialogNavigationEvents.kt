package com.aya.digital.feature.bottomdialogs.createappointmentdialog.navigation

import com.aya.digital.core.data.base.result.models.code.CodeResultModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class CreateAppointmentDialogNavigationEvents : CoordinatorEvent() {
    data class FinishWithResult(val requestCode:String,val result: CodeResultModel) : CreateAppointmentDialogNavigationEvents()
    object Exit : CreateAppointmentDialogNavigationEvents()


}
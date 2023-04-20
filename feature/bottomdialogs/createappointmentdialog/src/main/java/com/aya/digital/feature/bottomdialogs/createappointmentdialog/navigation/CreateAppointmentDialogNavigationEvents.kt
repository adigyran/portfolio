package com.aya.digital.feature.bottomdialogs.createappointmentdialog.navigation

import com.aya.digital.core.data.base.result.models.appointment.CreateAppointmentResultModel
import com.aya.digital.core.data.base.result.models.code.CodeResultModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class CreateAppointmentDialogNavigationEvents : CoordinatorEvent() {
    data class FinishWithResult(val requestCode:String,val result: CreateAppointmentResultModel) : CreateAppointmentDialogNavigationEvents()

    object Exit : CreateAppointmentDialogNavigationEvents()


}
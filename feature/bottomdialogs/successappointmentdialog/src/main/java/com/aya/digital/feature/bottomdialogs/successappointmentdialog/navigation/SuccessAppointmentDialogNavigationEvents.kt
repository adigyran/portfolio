package com.aya.digital.feature.bottomdialogs.successappointmentdialog.navigation

import com.aya.digital.core.data.base.result.models.appointment.CreateAppointmentResultModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class SuccessAppointmentDialogNavigationEvents : CoordinatorEvent() {
    data class FinishWithResult(val requestCode:String,val result: CreateAppointmentResultModel) : SuccessAppointmentDialogNavigationEvents()

    object Exit : SuccessAppointmentDialogNavigationEvents()


}
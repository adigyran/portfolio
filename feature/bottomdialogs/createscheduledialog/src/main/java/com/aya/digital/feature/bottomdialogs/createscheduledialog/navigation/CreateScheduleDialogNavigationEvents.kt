package com.aya.digital.feature.bottomdialogs.createscheduledialog.navigation

import com.aya.digital.core.data.base.result.models.appointment.CreateAppointmentResultModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class CreateScheduleDialogNavigationEvents : CoordinatorEvent() {
    data class FinishWithResult(val requestCode:String,val result: Boolean) : CreateScheduleDialogNavigationEvents()

    object Exit : CreateScheduleDialogNavigationEvents()


}
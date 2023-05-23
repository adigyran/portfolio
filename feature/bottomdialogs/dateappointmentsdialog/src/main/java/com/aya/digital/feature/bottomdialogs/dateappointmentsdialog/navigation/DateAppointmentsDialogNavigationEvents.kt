package com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.navigation

import com.aya.digital.core.data.base.result.models.appointment.CreateAppointmentResultModel
import com.aya.digital.core.data.base.result.models.appointment.SelectAppointmentResultModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class DateAppointmentsDialogNavigationEvents : CoordinatorEvent() {
    data class FinishWithResult(val requestCode:String,val result: SelectAppointmentResultModel) : DateAppointmentsDialogNavigationEvents()
    data class OpenAppointment(val appointmentId:Int):DateAppointmentsDialogNavigationEvents()

    object Exit : DateAppointmentsDialogNavigationEvents()


}
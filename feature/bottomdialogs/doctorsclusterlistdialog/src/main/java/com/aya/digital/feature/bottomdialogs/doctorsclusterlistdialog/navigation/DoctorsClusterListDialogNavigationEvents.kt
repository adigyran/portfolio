package com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.navigation

import com.aya.digital.core.data.base.result.models.appointment.CreateAppointmentResultModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class DoctorsClusterListDialogNavigationEvents : CoordinatorEvent() {
    data class FinishWithResult(val requestCode:String,val result: CreateAppointmentResultModel) : DoctorsClusterListDialogNavigationEvents()

    data class OpenDoctorCard(val doctorId: Int) : DoctorsClusterListDialogNavigationEvents()

    object Exit : DoctorsClusterListDialogNavigationEvents()


}
package com.aya.digital.feature.bottomdialogs.successappointmentdialog.navigation

import com.aya.digital.core.navigation.screen.HealthAppDialogFragmentScreen
import com.aya.digital.feature.bottomdialogs.successappointmentdialog.ui.SuccessAppointmentDialogView
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

class SuccessAppointmentDialogScreen(
    tag: String,
    private val requestCode: String,
    private val appointmentId:Int
) :
    HealthAppDialogFragmentScreen(tag = tag, fragmentCreator = {
        SuccessAppointmentDialogView.getNewInstance(
            requestCode =  requestCode,
            appointmentId = appointmentId
        )
    })
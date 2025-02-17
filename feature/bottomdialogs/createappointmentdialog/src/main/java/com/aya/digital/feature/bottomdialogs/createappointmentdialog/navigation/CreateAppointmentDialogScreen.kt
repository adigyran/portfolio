package com.aya.digital.feature.bottomdialogs.createappointmentdialog.navigation

import com.aya.digital.core.navigation.screen.HealthAppDialogFragmentScreen
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.ui.CreateAppointmentDialogView
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

class CreateAppointmentDialogScreen(
    tag: String,
    val requestCode: String,
    val doctorId:Int,
    val slotDateTime: LocalDateTime?,
    val date: LocalDate
) :
    HealthAppDialogFragmentScreen(tag = tag, fragmentCreator = {
        CreateAppointmentDialogView.getNewInstance(
            requestCode =  requestCode,
            doctorId = doctorId,
            slotDateTime = slotDateTime,
            date = date
        )
    })
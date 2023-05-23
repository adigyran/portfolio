package com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.navigation

import com.aya.digital.core.navigation.screen.HealthAppDialogFragmentScreen
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.ui.DateAppointmentsDialogView
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

class DateAppointmentsDialogScreen(
    tag: String,
    val requestCode: String,
    val date: LocalDate
) :
    HealthAppDialogFragmentScreen(tag = tag, fragmentCreator = {
        DateAppointmentsDialogView.getNewInstance(
            requestCode = requestCode,
            date = date
        )
    })
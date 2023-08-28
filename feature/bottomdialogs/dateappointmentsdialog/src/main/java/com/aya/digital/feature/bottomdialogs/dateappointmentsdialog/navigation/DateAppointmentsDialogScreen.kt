package com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.navigation

import com.aya.digital.core.navigation.screen.HealthAppDialogFragmentScreen
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.ui.DateAppointmentsDialogView
import java.time.LocalDate
import java.time.LocalDateTime

class DateAppointmentsDialogScreen(
    tag: String,
    private val requestCode: String,
    private val dialogParam: DateAppointmentsDialogView.DialogParam
) :
    HealthAppDialogFragmentScreen(tag = tag, fragmentCreator = {
        DateAppointmentsDialogView.getNewInstance(
            requestCode = requestCode,
            dialogParam = dialogParam
        )
    })
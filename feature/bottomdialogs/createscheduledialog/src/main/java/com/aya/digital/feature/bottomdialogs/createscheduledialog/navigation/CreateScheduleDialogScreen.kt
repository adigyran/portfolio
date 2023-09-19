package com.aya.digital.feature.bottomdialogs.createscheduledialog.navigation

import com.aya.digital.core.navigation.screen.HealthAppDialogFragmentScreen
import com.aya.digital.feature.bottomdialogs.createscheduledialog.ui.CreateScheduleDialogView
import java.time.LocalDate

class CreateScheduleDialogScreen(
    tag: String,
    private val requestCode:String,
    private val date: LocalDate
) :
    HealthAppDialogFragmentScreen(tag = tag, fragmentCreator = {
        CreateScheduleDialogView.getNewInstance(
            requestCode = requestCode,
            date = date
        )
    })
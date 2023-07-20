package com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.navigation

import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.navigation.screen.HealthAppDialogFragmentScreen
import com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.ui.DoctorsClusterListDialogView
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

class DoctorsClusterListDialogScreen(
    tag: String,
    val requestCode: String,
    val doctors:List<DoctorModel>
) :
    HealthAppDialogFragmentScreen(tag = tag, fragmentCreator = {
        DoctorsClusterListDialogView.getNewInstance(
            requestCode =  requestCode,
            doctors = doctors
        )
    })
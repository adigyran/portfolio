package com.aya.digital.feature.bottomdialogs.successappointmentdialog.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.feature.bottomdialogs.successappointmentdialog.viewmodel.SuccessAppointmentDialogViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.bottomdialogs.successappointmentdialog.ui.SuccessAppointmentDialogView
import com.aya.digital.feature.bottomdialogs.successappointmentdialog.ui.model.SuccessAppointmentDialogStateTransformer
import org.kodein.di.*

fun successAppointmentDialogDiModule(
    parentCoordinatorEvent: CoordinatorRouter,
    param: SuccessAppointmentDialogView.Param
) = DI.Module("successAppointmentDialogDiModule") {

    bind<SuccessAppointmentDialogStateTransformer>() with singleton {
        SuccessAppointmentDialogStateTransformer(
            instance(),
            instance()
        )
    }


    bind {
        scoped(CustomFragmentScope).singleton {
            SuccessAppointmentDialogViewModel(parentCoordinatorEvent, param, instance())
        }
    }

}


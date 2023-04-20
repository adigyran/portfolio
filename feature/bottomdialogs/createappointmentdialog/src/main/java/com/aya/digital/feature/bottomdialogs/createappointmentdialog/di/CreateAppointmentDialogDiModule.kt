package com.aya.digital.feature.bottomdialogs.createappointmentdialog.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.viewmodel.CreateAppointmentDialogViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.ui.CreateAppointmentDialogView
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.ui.model.CreateAppointmentDialogStateTransformer
import org.kodein.di.*

fun createAppointmentDialogDiModule(
    parentCoordinatorEvent: CoordinatorRouter,
    param: CreateAppointmentDialogView.Param
) = DI.Module("createAppointmentDialogDiModule") {

    bind<CreateAppointmentDialogStateTransformer>() with singleton {
        CreateAppointmentDialogStateTransformer(
            instance(),
            instance()
        )
    }


    bind {
        scoped(CustomFragmentScope).singleton {
            CreateAppointmentDialogViewModel(parentCoordinatorEvent, param, instance(), instance())
        }
    }

}


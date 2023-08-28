package com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.viewmodel.DateAppointmentsDialogViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.ui.DateAppointmentsDialogView
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.ui.model.DateAppointmentsDialogStateTransformer
import org.kodein.di.*

fun dateAppointmentsDialogDiModule(
    parentCoordinatorEvent: CoordinatorRouter,
    param: DateAppointmentsDialogView.Param
) = DI.Module("dateAppointmentsDialogDiModule") {

    bind<DateAppointmentsDialogStateTransformer>() with singleton {
        DateAppointmentsDialogStateTransformer(
            context = instance(),
            param = param,
            dateTimeUtils = instance(),
        )
    }


    bind {
        scoped(CustomFragmentScope).singleton {
            DateAppointmentsDialogViewModel(
                coordinatorRouter = parentCoordinatorEvent,
                param = param,
                getAppointmentsByIntervalUseCase = instance(),
                getAppointmentsByStartDayAndDaysUseCase = instance()
            )
        }
    }

}


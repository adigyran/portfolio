package com.aya.digital.feature.bottomdialogs.createscheduledialog.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.feature.bottomdialogs.createscheduledialog.viewmodel.CreateScheduleDialogViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.bottomdialogs.createscheduledialog.ui.CreateScheduleDialogView
import com.aya.digital.feature.bottomdialogs.createscheduledialog.ui.model.CreateScheduleDialogStateTransformer
import org.kodein.di.*

fun createScheduleDialogDiModule(
    parentCoordinatorEvent: CoordinatorRouter,
    param: CreateScheduleDialogView.Param
) = DI.Module("createScheduleDialogDiModule") {

    bind<CreateScheduleDialogStateTransformer>() with singleton {
        CreateScheduleDialogStateTransformer(
            context = instance(),
            dateTimeUtils = instance()
        )
    }


    bind {
        scoped(CustomFragmentScope).singleton {
            CreateScheduleDialogViewModel(
                coordinatorRouter = parentCoordinatorEvent,
                param = param,
                createDaySlotsScheduleUseCase = instance()
            )
        }
    }

}


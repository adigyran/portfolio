package com.aya.digital.core.feature.appointments.appointmentcard.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.appointments.appointmentcard.ui.AppointmentCardView
import com.aya.digital.core.feature.appointments.appointmentcard.ui.model.AppointmentCardStateTransformer
import com.aya.digital.core.feature.appointments.appointmentcard.viewmodel.AppointmentCardViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

internal fun appointmentCardDiModule(
    parentCoordinatorEvent: CoordinatorRouter,
    param: AppointmentCardView.Param
) = DI.Module("appointmentCardDiModule") {

    bind<AppointmentCardStateTransformer>() with singleton {
        AppointmentCardStateTransformer(
            instance(),
            instance(),
            instance()
        )
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            AppointmentCardViewModel(
                parentCoordinatorEvent,
                instance("parent_coordinator_bottomnav"),
                param,
                instance(),
                instance(),
                instance()
            )
        }
    }
}
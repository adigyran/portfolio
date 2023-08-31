package com.aya.digital.core.feature.tabviews.home.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.tabviews.home.ui.model.HomeStateTransformer
import com.aya.digital.core.feature.tabviews.home.viewmodel.HomeViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun homeDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("homeDiModule") {

    bind<HomeStateTransformer>() with singleton { HomeStateTransformer(instance()) }

    bind {
        scoped(CustomFragmentScope).singleton {
            HomeViewModel(
                coordinatorRouter = parentCoordinatorEvent,
                getLastUpdatesUseCase = instance(),
                getAppointmentsUseCase = instance(),
                getDoctorsUseCase = instance()
            )
        }
    }
}
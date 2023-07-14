package com.aya.digital.core.feature.tabviews.doctorsearchcontainer.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.viewmodel.DoctorSearchContainerViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun doctorSearchContainerDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("doctorSearchContainerDiModule") {

    bind {
        scoped(CustomFragmentScope).singleton {
            DoctorSearchContainerViewModel(
                parentCoordinatorEvent,
                instance("parent_coordinator_bottomnav"),
                instance(),
                instance(),
                instance(),
                instance()
            )
        }
    }
}
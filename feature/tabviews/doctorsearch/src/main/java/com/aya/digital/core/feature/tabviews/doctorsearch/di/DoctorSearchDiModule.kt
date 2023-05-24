package com.aya.digital.core.feature.tabviews.doctorsearch.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.tabviews.doctorsearch.ui.model.DoctorSearchStateTransformer
import com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel.DoctorSearchViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun doctorSearchDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("doctorSearchDiModule") {

    bind<DoctorSearchStateTransformer>() with singleton { DoctorSearchStateTransformer(instance()) }

    bind {
        scoped(CustomFragmentScope).singleton {
            DoctorSearchViewModel(parentCoordinatorEvent,instance("parent_coordinator_bottomnav"),instance())
        }
    }
}
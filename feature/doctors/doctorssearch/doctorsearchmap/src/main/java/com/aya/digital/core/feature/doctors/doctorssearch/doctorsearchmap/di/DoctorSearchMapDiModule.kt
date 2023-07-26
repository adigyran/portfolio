package com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.ui.model.DoctorSearchMapStateTransformer
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.viewmodel.DoctorSearchMapViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun doctorSearchMapDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("doctorSearchMapDiModule") {

    bind<DoctorSearchMapStateTransformer>() with singleton { DoctorSearchMapStateTransformer(instance()) }

    bind {
        scoped(CustomFragmentScope).singleton {
            DoctorSearchMapViewModel(
                parentCoordinatorEvent,
                instance("parent_coordinator_bottomnav"),
                instance(),
                instance(),
                instance(),
                instance(),
                instance(),
                instance()
            )
        }
    }
}
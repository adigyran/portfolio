package com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.ui.model.DoctorSearchListStateTransformer
import com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.viewmodel.DoctorSearchListViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun doctorSearchListDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("doctorSearchListDiModule") {

    bind<DoctorSearchListStateTransformer>() with singleton { DoctorSearchListStateTransformer(instance()) }

    bind {
        scoped(CustomFragmentScope).singleton {
            DoctorSearchListViewModel(
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
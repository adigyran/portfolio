package com.aya.digital.core.feature.doctors.doctorcard.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.doctors.doctorcard.ui.DoctorCardView
import com.aya.digital.core.feature.doctors.doctorcard.ui.model.DoctorCardStateTransformer
import com.aya.digital.core.feature.doctors.doctorcard.viewmodel.DoctorCardViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun doctorCardDiModule(
    parentCoordinatorEvent: CoordinatorRouter,
    param: DoctorCardView.Param
) = DI.Module("doctorCardDiModule") {

    bind<DoctorCardStateTransformer>() with singleton {
        DoctorCardStateTransformer(
            instance(),
            instance()
        )
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            DoctorCardViewModel(
                parentCoordinatorEvent,
               // instance("parent_coordinator_bottomnav"),
                param,
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
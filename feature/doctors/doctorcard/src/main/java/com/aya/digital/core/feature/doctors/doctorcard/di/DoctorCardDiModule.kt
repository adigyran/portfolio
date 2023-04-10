package com.aya.digital.core.feature.doctors.doctorcard.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.doctors.doctorcard.ui.model.DoctorCardStateTransformer
import com.aya.digital.core.feature.doctors.doctorcard.viewmodel.DoctorCardViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun doctorCardDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("doctorCardDiModule") {

    bind<DoctorCardStateTransformer>() with singleton {
        DoctorCardStateTransformer(
            instance()
        )
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            DoctorCardViewModel(parentCoordinatorEvent)
        }
    }
}
package com.aya.digital.core.feature.insurance.list.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.insurance.list.ui.model.ProfileInsuranceDoctorStateTransformer
import com.aya.digital.core.feature.insurance.list.viewmodel.ProfileInsuranceDoctorViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun profileInsuranceDoctorDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("profileInsuranceDoctor") {

    bind<ProfileInsuranceDoctorStateTransformer>() with singleton {
        ProfileInsuranceDoctorStateTransformer(
            instance()
        )
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            ProfileInsuranceDoctorViewModel(
                parentCoordinatorEvent,
                instance(),
                instance(),
                instance("parent_coordinator_bottomnav")
            )
        }
    }
}
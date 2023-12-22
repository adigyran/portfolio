package com.aya.digital.core.feature.profile.prescriptions.view.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.profile.prescriptions.view.ui.ProfileInsuranceAddView
import com.aya.digital.core.feature.profile.prescriptions.view.ui.model.ProfileInsuranceAddStateTransformer
import com.aya.digital.core.feature.profile.prescriptions.view.viewmodel.ProfileInsuranceAddViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun profileInsuranceAddDiModule(
    parentCoordinatorEvent: CoordinatorRouter,
    param: ProfileInsuranceAddView.Param
) = DI.Module("profileInsuranceAdd") {

    bind<ProfileInsuranceAddStateTransformer>() with singleton {
        ProfileInsuranceAddStateTransformer(
            instance()
        )
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            ProfileInsuranceAddViewModel(
                param,
                parentCoordinatorEvent,
                instance("parent_coordinator_bottomnav"),
                instance(),
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
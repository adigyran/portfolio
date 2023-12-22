package com.aya.digital.core.feature.profile.prescriptions.edit.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.profile.prescriptions.edit.ui.ProfileInsuranceAddView
import com.aya.digital.core.feature.profile.prescriptions.edit.ui.model.ProfileInsuranceAddStateTransformer
import com.aya.digital.core.feature.profile.prescriptions.edit.viewmodel.ProfileInsuranceAddViewModel
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
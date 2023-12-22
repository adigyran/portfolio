package com.aya.digital.core.feature.profile.prescriptions.edit.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.profile.prescriptions.edit.ui.ProfileInsuranceAddView
import com.aya.digital.core.feature.profile.prescriptions.edit.ui.model.ProfilePrescriptionsEditStateTransformer
import com.aya.digital.core.feature.profile.prescriptions.edit.viewmodel.ProfilePrescriptionsEditViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun profilePrescriptionsEditDiModule(
    parentCoordinatorEvent: CoordinatorRouter,
    param: ProfileInsuranceAddView.Param
) = DI.Module("profilePrescriptionsEdit") {

    bind<ProfilePrescriptionsEditStateTransformer>() with singleton {
        ProfilePrescriptionsEditStateTransformer(
            instance()
        )
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            ProfilePrescriptionsEditViewModel(
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
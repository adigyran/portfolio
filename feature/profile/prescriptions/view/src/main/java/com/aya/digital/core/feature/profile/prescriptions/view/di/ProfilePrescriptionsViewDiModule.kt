package com.aya.digital.core.feature.profile.prescriptions.view.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.profile.prescriptions.view.ui.ProfileInsuranceAddView
import com.aya.digital.core.feature.profile.prescriptions.view.ui.model.ProfilePrescriptionsViewStateTransformer
import com.aya.digital.core.feature.profile.prescriptions.view.viewmodel.ProfilePrescriptionsViewViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun profilePrescriptionsViewDiModule(
    parentCoordinatorEvent: CoordinatorRouter,
    param: ProfileInsuranceAddView.Param
) = DI.Module("profilePrescriptionsView") {

    bind<ProfilePrescriptionsViewStateTransformer>() with singleton {
        ProfilePrescriptionsViewStateTransformer(
            instance()
        )
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            ProfilePrescriptionsViewViewModel(
                param,
                parentCoordinatorEvent,
                instance("parent_coordinator_bottomnav")
            )
        }
    }
}
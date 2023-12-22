package com.aya.digital.core.feature.prescriptions.list.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.prescriptions.list.ui.model.ProfilePrescriptionsListStateTransformer
import com.aya.digital.core.feature.prescriptions.list.viewmodel.ProfilePrescriptionsListViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun profilePrescriptionsListDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("profilePrescriptionsList") {

    bind<ProfilePrescriptionsListStateTransformer>() with singleton {
        ProfilePrescriptionsListStateTransformer(
            instance()
        )
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            ProfilePrescriptionsListViewModel(parentCoordinatorEvent,instance(),instance())
        }
    }
}
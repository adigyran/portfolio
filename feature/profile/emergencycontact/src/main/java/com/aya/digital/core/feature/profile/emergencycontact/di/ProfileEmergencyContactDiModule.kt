package com.aya.digital.core.feature.profile.emergencycontact.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.profile.emergencycontact.ui.model.ProfileEmergencyContactStateTransformer
import com.aya.digital.core.feature.profile.emergencycontact.viewmodel.ProfileEmergencyContactViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun profileEmergencyContactDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("profileEmergencyContactDiModule") {

    bind<ProfileEmergencyContactStateTransformer>() with singleton {
        ProfileEmergencyContactStateTransformer(
            instance()
        )
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            ProfileEmergencyContactViewModel(
                parentCoordinatorEvent, instance("parent_coordinator_bottomnav"),
                instance(), instance(), instance(),instance(),instance()
            )
        }
    }
}
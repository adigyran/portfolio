package com.aya.digital.core.feature.profile.security.changeemail.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.profile.security.changeemail.ui.ProfileSecurityChangeEmailView
import com.aya.digital.core.feature.profile.security.changeemail.ui.model.ProfileSecurityChangeEmailStateTransformer
import com.aya.digital.core.feature.profile.security.changeemail.viewmodel.ProfileSecurityChangeEmailViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

internal fun profileSecurityChangeEmailDiModule(
    parentCoordinatorEvent: CoordinatorRouter,
    param: ProfileSecurityChangeEmailView.Param
) = DI.Module("profileSecurityChangeEmailDiModule") {

    bind<ProfileSecurityChangeEmailStateTransformer>() with singleton {
        ProfileSecurityChangeEmailStateTransformer(
            instance()
        )
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            ProfileSecurityChangeEmailViewModel(
                param,
                instance("parent_coordinator_bottomnav"),
                parentCoordinatorEvent,
                instance(),
                instance()
            )
        }
    }
}
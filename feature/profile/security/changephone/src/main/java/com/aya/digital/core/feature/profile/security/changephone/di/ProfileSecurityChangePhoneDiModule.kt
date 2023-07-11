package com.aya.digital.core.feature.profile.security.changephone.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.profile.security.changephone.ui.ProfileSecurityChangePhoneView
import com.aya.digital.core.feature.profile.security.changephone.ui.model.ProfileSecurityChangePhoneStateTransformer
import com.aya.digital.core.feature.profile.security.changephone.viewmodel.ProfileSecurityChangePhoneViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

internal fun profileSecurityChangePhoneDiModule(
    parentCoordinatorEvent: CoordinatorRouter,
    param: ProfileSecurityChangePhoneView.Param
) = DI.Module("profileSecurityChangePhoneDiModule") {

    bind<ProfileSecurityChangePhoneStateTransformer>() with singleton {
        ProfileSecurityChangePhoneStateTransformer(
            instance()
        )
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            ProfileSecurityChangePhoneViewModel(
                param,
                instance("parent_coordinator_bottomnav"),
                parentCoordinatorEvent,
                instance(),
                instance(),
                instance(),
                instance()
            )
        }
    }
}
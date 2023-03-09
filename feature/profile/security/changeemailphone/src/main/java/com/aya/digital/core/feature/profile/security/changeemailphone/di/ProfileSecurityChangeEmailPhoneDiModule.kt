package com.aya.digital.core.feature.profile.security.changeemailphone.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.profile.security.changeemailphone.ui.ProfileSecurityChangeEmailPhoneView
import com.aya.digital.core.feature.profile.security.changeemailphone.ui.model.ProfileSecurityChangeEmailPhoneStateTransformer
import com.aya.digital.core.feature.profile.security.changeemailphone.viewmodel.ProfileSecurityChangeEmailPhoneViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

internal fun profileSecurityChangeEmailPhoneDiModule(
    parentCoordinatorEvent: CoordinatorRouter,
    param: ProfileSecurityChangeEmailPhoneView.Param
) = DI.Module("profileSecurityChangeEmailPhoneDiModule") {

    bind<ProfileSecurityChangeEmailPhoneStateTransformer>() with singleton {
        ProfileSecurityChangeEmailPhoneStateTransformer(
            instance()
        )
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            ProfileSecurityChangeEmailPhoneViewModel(
                param,
                parentCoordinatorEvent,
                instance(),
                instance()
            )
        }
    }
}
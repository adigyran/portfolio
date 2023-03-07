package com.aya.digital.core.feature.profile.security.changeemailphone.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.profile.security.changeemailphone.ui.model.ProfileSecurityChangeEmailPhoneStateTransformer
import com.aya.digital.core.feature.profile.security.changeemailphone.viewmodel.ProfileSecurityChangeEmailPhoneViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun ProfileSecurityChangeEmailPhoneDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("profileSecurityChangeEmailPhoneDiModule") {

    bind<ProfileSecurityChangeEmailPhoneStateTransformer>() with singleton { ProfileSecurityChangeEmailPhoneStateTransformer(instance()) }

    bind {
        scoped(CustomFragmentScope).singleton {
            ProfileSecurityChangeEmailPhoneViewModel(parentCoordinatorEvent,instance(),instance())
        }
    }
}
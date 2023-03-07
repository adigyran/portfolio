package com.aya.digital.core.feature.profile.security.changepassword.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.profile.security.changepassword.ui.model.ProfileSecurityChangePasswordStateTransformer
import com.aya.digital.core.feature.profile.security.changepassword.viewmodel.ProfileSecurityChangePasswordViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun profileSecurityChangePasswordDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("profileSecurityChangePasswordDiModule") {

    bind<ProfileSecurityChangePasswordStateTransformer>() with singleton { ProfileSecurityChangePasswordStateTransformer(instance()) }

    bind {
        scoped(CustomFragmentScope).singleton {
            ProfileSecurityChangePasswordViewModel(parentCoordinatorEvent,instance())
        }
    }
}
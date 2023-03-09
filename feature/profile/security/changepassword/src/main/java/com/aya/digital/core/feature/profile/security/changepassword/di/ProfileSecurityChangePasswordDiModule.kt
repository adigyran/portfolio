package com.aya.digital.core.feature.profile.security.changepassword.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.profile.security.changepassword.ui.ProfileSecurityChangePasswordView
import com.aya.digital.core.feature.profile.security.changepassword.ui.model.ProfileSecurityChangePasswordStateTransformer
import com.aya.digital.core.feature.profile.security.changepassword.viewmodel.ProfileSecurityChangePasswordViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

internal fun profileSecurityChangePasswordDiModule(
    parentCoordinatorEvent: CoordinatorRouter,
    param: ProfileSecurityChangePasswordView.Param
) = DI.Module("profileSecurityChangePasswordDiModule") {

    bind<ProfileSecurityChangePasswordStateTransformer>() with singleton {
        ProfileSecurityChangePasswordStateTransformer(
            instance()
        )
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            ProfileSecurityChangePasswordViewModel(param, parentCoordinatorEvent, instance())
        }
    }
}
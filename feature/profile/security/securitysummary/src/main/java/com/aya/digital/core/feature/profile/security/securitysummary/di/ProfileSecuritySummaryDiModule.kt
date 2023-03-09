package com.aya.digital.core.feature.profile.security.securitysummary.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.profile.security.securitysummary.ui.model.ProfileSecuritySummaryStateTransformer
import com.aya.digital.core.feature.profile.security.securitysummary.viewmodel.ProfileSecuritySummaryViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

internal fun profileSecuritySummaryDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("profileSecuritySummaryDiModule") {

    bind<ProfileSecuritySummaryStateTransformer>() with singleton {
        ProfileSecuritySummaryStateTransformer(
            instance()
        )
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            ProfileSecuritySummaryViewModel(parentCoordinatorEvent, instance())
        }
    }
}
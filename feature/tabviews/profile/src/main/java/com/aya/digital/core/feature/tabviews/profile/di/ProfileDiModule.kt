package com.aya.digital.core.feature.tabviews.profile.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.tabviews.profile.ui.model.ProfileStateTransformer
import com.aya.digital.core.feature.tabviews.profile.viewmodel.ProfileViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun profileDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("profileDiModule") {

    bind<ProfileStateTransformer>() with singleton { ProfileStateTransformer(instance(),instance()) }

    bind {
        scoped(CustomFragmentScope).singleton {
            ProfileViewModel(parentCoordinatorEvent,instance())
        }
    }
}
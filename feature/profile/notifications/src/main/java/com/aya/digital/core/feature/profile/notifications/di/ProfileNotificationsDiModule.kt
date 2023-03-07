package com.aya.digital.core.feature.profile.notifications.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.profile.notifications.ui.model.ProfileNotificationsStateTransformer
import com.aya.digital.core.feature.profile.notifications.viewmodel.ProfileNotificationsViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun profileNotificationsDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("profileNotificationsSummaryDiModule") {

    bind<ProfileNotificationsStateTransformer>() with singleton { ProfileNotificationsStateTransformer(instance()) }

    bind {
        scoped(CustomFragmentScope).singleton {
            ProfileNotificationsViewModel(parentCoordinatorEvent,instance(),instance())
        }
    }
}
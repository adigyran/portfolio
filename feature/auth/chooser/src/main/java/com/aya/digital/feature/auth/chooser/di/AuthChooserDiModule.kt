package com.aya.digital.feature.auth.chooser.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.auth.chooser.ui.model.AuthChooserStateTransformer
import com.aya.digital.feature.auth.chooser.viewmodel.AuthChooserViewModel
import org.kodein.di.*

fun authChooserDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("authChooserDiModule") {

    bind<AuthChooserStateTransformer>() with singleton { AuthChooserStateTransformer(instance()) }

    bind {
        scoped(CustomFragmentScope).singleton {
            AuthChooserViewModel(parentCoordinatorEvent)
        }
    }
}
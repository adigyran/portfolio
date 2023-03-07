package com.aya.digital.core.feature.auth.signin.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.auth.signin.ui.model.RestorePasswordStateTransformer
import com.aya.digital.core.feature.auth.signin.viewmodel.RestorePasswordViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun restorePasswordDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("restorePasswordDiModule") {

    bind<RestorePasswordStateTransformer>() with singleton { RestorePasswordStateTransformer(instance()) }

    bind {
        scoped(CustomFragmentScope).singleton {
            RestorePasswordViewModel(parentCoordinatorEvent)
        }
    }
}
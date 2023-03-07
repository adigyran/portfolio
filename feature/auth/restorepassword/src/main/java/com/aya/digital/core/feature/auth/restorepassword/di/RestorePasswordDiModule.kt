package com.aya.digital.core.feature.auth.restorepassword.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.auth.restorepassword.ui.RestorePasswordView
import com.aya.digital.core.feature.auth.restorepassword.ui.model.RestorePasswordStateTransformer
import com.aya.digital.core.feature.auth.restorepassword.viewmodel.RestorePasswordViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun restorePasswordDiModule(
    parentCoordinatorEvent: CoordinatorRouter,
    param: RestorePasswordView.Param
) = DI.Module("restorePasswordDiModule") {

    bind<RestorePasswordStateTransformer>() with singleton { RestorePasswordStateTransformer(instance()) }

    bind {
        scoped(CustomFragmentScope).singleton {
            RestorePasswordViewModel(parentCoordinatorEvent,instance("parent_coordinator_auth_container"),param,instance(),instance(),instance())
        }
    }
}
package com.aya.digital.feature.auth.signup.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.auth.signup.ui.model.SignUpStateTransformer
import com.aya.digital.feature.auth.signup.viewmodel.SignUpViewModel
import org.kodein.di.*

fun signUpDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("SignUpDiModule") {

    bind<SignUpStateTransformer>() with singleton { SignUpStateTransformer(instance()) }

    bind {
        scoped(CustomFragmentScope).singleton {
            SignUpViewModel(parentCoordinatorEvent,instance("parent_coordinator_auth_container"),instance(),instance(),instance(),instance(),instance())
        }
    }
}
package com.aya.digital.core.feature.auth.signin.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.auth.signin.ui.model.SignInStateTransformer
import com.aya.digital.core.feature.auth.signin.viewmodel.SignInViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun signInDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("signInDiModule") {

    bind<SignInStateTransformer>() with singleton { SignInStateTransformer(instance()) }

    bind {
        scoped(CustomFragmentScope).singleton {
            SignInViewModel(parentCoordinatorEvent,instance())
        }
    }
}
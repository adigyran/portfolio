package com.aya.digital.feature.auth.chooser.di

import androidx.fragment.app.Fragment
import com.aya.digital.core.dibase.scopes.CustomActivityScope
import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.auth.chooser.viewmodel.AuthChooserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.scoped
import org.kodein.di.singleton

fun authChooserDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("authChooserDiModule") {


    bind {
        scoped(CustomFragmentScope).singleton {
            AuthChooserViewModel(parentCoordinatorEvent)
        }
    }
}
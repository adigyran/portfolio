package com.aya.digital.feature.bottomdialog.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.feature.bottomdialog.viewmodel.BottomDialogViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun bottomDialogDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("BottomDialogDiModule") {


    bind {
        scoped(CustomFragmentScope).singleton {
            BottomDialogViewModel(parentCoordinatorEvent)
        }
    }

}


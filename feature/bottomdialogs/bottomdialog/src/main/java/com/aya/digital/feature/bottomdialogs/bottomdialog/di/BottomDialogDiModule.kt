package com.aya.digital.feature.bottomdialogs.bottomdialog.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.feature.bottomdialogs.bottomdialog.viewmodel.BottomDialogViewModel
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


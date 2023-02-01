package com.aya.digital.core.feature.bottomdialog.di

import com.aya.digital.core.dibase.scopes.CustomActivityScope
import com.aya.digital.core.feature.bottomdialog.viewmodel.BottomDialogViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun bottomDialogDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("BottomDialogDiModule") {


    bind {
        scoped(CustomActivityScope).singleton {
            BottomDialogViewModel(parentCoordinatorEvent)
        }
    }

}


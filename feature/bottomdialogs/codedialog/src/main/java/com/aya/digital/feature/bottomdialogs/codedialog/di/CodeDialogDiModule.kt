package com.aya.digital.feature.bottomdialogs.codedialog.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.feature.bottomdialogs.codedialog.viewmodel.CodeDialogViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.bottomdialogs.codedialog.ui.CodeDialogView
import com.aya.digital.feature.bottomdialogs.codedialog.ui.model.CodeDialogStateTransformer
import org.kodein.di.*

fun codeDialogDiModule(
    parentCoordinatorEvent: CoordinatorRouter,
    param: CodeDialogView.Param
) = DI.Module("codeDialogDiModule") {

    bind<CodeDialogStateTransformer>() with singleton { CodeDialogStateTransformer(instance()) }


    bind {
        scoped(CustomFragmentScope).singleton {
            CodeDialogViewModel(parentCoordinatorEvent,param)
        }
    }

}


package com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.viewmodel.DoctorsClusterListDialogViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.ui.DoctorsClusterListDialogView
import com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.ui.model.DoctorsClusterListDialogStateTransformer
import org.kodein.di.*

fun doctorsClusterListDialogDiModule(
    parentCoordinatorEvent: CoordinatorRouter,
    param: DoctorsClusterListDialogView.Param
) = DI.Module("doctorsClusterListDialogDiModule") {

    bind<DoctorsClusterListDialogStateTransformer>() with singleton {
        DoctorsClusterListDialogStateTransformer(
            instance(),
            instance()
        )
    }


    bind {
        scoped(CustomFragmentScope).singleton {
            DoctorsClusterListDialogViewModel(parentCoordinatorEvent, param, instance(), instance(),instance())
        }
    }

}


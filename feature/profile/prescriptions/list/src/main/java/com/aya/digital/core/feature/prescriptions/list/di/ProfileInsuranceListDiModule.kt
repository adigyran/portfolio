package com.aya.digital.core.feature.prescriptions.list.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.prescriptions.list.ui.model.ProfileInsuranceListStateTransformer
import com.aya.digital.core.feature.prescriptions.list.viewmodel.ProfileInsuranceListViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun profileInsuranceListDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("profileInsuranceList") {

    bind<ProfileInsuranceListStateTransformer>() with singleton {
        ProfileInsuranceListStateTransformer(
            instance()
        )
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            ProfileInsuranceListViewModel(parentCoordinatorEvent,instance(),instance())
        }
    }
}
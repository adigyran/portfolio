package com.aya.digital.core.feature.profile.generalinfo.view.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.profile.generalinfo.view.ui.ProfileGeneralInfoViewView
import com.aya.digital.core.feature.profile.generalinfo.view.ui.model.ProfileGeneralInfoViewStateTransformer
import com.aya.digital.core.feature.profile.generalinfo.view.viewmodel.ProfileGeneralInfoViewViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun profileGeneralInfoViewDiModule(
    param:ProfileGeneralInfoViewView.Param,
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("profileGeneralInfoViewDiModule") {

    bind<ProfileGeneralInfoViewStateTransformer>() with singleton {
        ProfileGeneralInfoViewStateTransformer(
            instance(),
            instance(),
            instance()
        )
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            ProfileGeneralInfoViewViewModel(param,parentCoordinatorEvent, instance(),instance())
        }
    }
}
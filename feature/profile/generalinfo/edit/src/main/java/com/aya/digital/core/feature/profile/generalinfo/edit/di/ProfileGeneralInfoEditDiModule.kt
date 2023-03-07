package com.aya.digital.core.feature.profile.generalinfo.edit.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.profile.generalinfo.edit.ui.model.ProfileGeneralInfoEditStateTransformer
import com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel.ProfileGeneralInfoEditViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun profileGeneralInfoEditDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("profileGeneralInfoEditDiModule") {

    bind<ProfileGeneralInfoEditStateTransformer>() with singleton { ProfileGeneralInfoEditStateTransformer(instance(),instance()) }

    bind {
        scoped(CustomFragmentScope).singleton {
            ProfileGeneralInfoEditViewModel(parentCoordinatorEvent,instance(),instance())
        }
    }
}
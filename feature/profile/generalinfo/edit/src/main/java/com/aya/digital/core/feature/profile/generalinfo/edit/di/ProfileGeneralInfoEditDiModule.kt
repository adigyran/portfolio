package com.aya.digital.core.feature.profile.generalinfo.edit.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.profile.generalinfo.edit.ui.ProfileGeneralInfoEditView
import com.aya.digital.core.feature.profile.generalinfo.edit.ui.model.ProfileGeneralInfoEditStateTransformer
import com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel.ProfileGeneralInfoEditViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun profileGeneralInfoEditDiModule(
    parentCoordinatorEvent: CoordinatorRouter,
    param: ProfileGeneralInfoEditView.Param
) = DI.Module("profileGeneralInfoEditDiModule") {

    bind<ProfileGeneralInfoEditStateTransformer>() with singleton {
        ProfileGeneralInfoEditStateTransformer(
            context = instance(),
            dateTimeUtils = instance(),
            appFlavour = instance()
        )
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            ProfileGeneralInfoEditViewModel(
                param = param,
                coordinatorRouter = parentCoordinatorEvent,
                rootCoordinatorRouter = instance(tag = "parent_coordinator_bottomnav"),
                profileInfoUseCase = instance(),
                saveProfileInfoUseCase = instance(),
                flavour = instance(),
                setAvatarUseCase = instance()
            )
        }
    }
}
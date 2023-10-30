package com.aya.digital.core.feature.profile.address.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.profile.address.ui.model.ProfileAddressStateTransformer
import com.aya.digital.core.feature.profile.address.viewmodel.ProfileAddressViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun profileAddressDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("profileAddressDiModule") {

    bind<ProfileAddressStateTransformer>() with singleton {
        ProfileAddressStateTransformer(
            instance()
        )
    }

    bind {
        scoped(CustomFragmentScope).singleton {
            ProfileAddressViewModel(
                coordinatorRouter = parentCoordinatorEvent,
                geocodeCoordinatesUseCase = instance(),
                getPlacesByAddressQueryUseCase = instance(),
                getProfileAddressUseCase = instance(),
                saveProfileAddressUseCase = instance(),
                getPlaceById = instance()
            )
        }
    }
}
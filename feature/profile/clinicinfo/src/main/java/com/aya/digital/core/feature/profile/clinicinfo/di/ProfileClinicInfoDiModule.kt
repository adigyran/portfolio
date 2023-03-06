package com.aya.digital.core.feature.profile.clinicinfo.di

import com.aya.digital.core.dibase.scopes.CustomFragmentScope
import com.aya.digital.core.feature.profile.clinicinfo.ui.model.ProfileClinicInfoStateTransformer
import com.aya.digital.core.feature.profile.clinicinfo.viewmodel.ProfileClinicInfoViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.kodein.di.*

fun profileClinicInfoDiModule(
    parentCoordinatorEvent: CoordinatorRouter
) = DI.Module("profileClinicInfoDiModule") {

    bind<ProfileClinicInfoStateTransformer>() with singleton { ProfileClinicInfoStateTransformer(instance()) }

    bind {
        scoped(CustomFragmentScope).singleton {
            ProfileClinicInfoViewModel(parentCoordinatorEvent)
        }
    }
}
package com.aya.digital.core.repository.profile

import com.aya.digital.core.data.profile.repository.PractitionerInsuranceRepository
import com.aya.digital.core.data.profile.repository.PractitionerRepository
import com.aya.digital.core.data.profile.repository.ProfileInsuranceRepository
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.repository.profile.main.ProfileInsuranceRepositoryImpl
import com.aya.digital.core.repository.profile.main.ProfileRepositoryImpl
import com.aya.digital.core.repository.profile.practitioner.PractitionerInsuranceRepositoryImpl
import com.aya.digital.core.repository.profile.practitioner.PractitionerRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun profileRepositoryDiModule() = DI.Module("profileRepositoryDiModule") {
    bind<ProfileRepository>() with singleton {
        ProfileRepositoryImpl(
            instance(),
            instance(),
            instance(),
            instance(),
            instance(),
            instance(),
            instance(),
            instance(),
            instance(),
            instance()
        )
    }

    bind<ProfileInsuranceRepository>() with singleton {
        ProfileInsuranceRepositoryImpl(
            instance(),
            instance()
        )
    }

    bind<PractitionerRepository>() with singleton {
        PractitionerRepositoryImpl(instance())
    }
    bind<PractitionerInsuranceRepository>() with singleton {
        PractitionerInsuranceRepositoryImpl(instance(),instance())
    }
}
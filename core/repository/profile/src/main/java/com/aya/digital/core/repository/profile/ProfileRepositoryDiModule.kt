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
            context = instance(),
            profileDataSource = instance(),
            tokenDataSource = instance(),
            appDataStore = instance(),
            currentProfileMapper = instance(),
            emergencyContactMapper = instance(),
            avatarMapper = instance(),
            invalidTokenEventManager = instance(),
            imageUploadResultMapper = instance(),
            notificationsStatusMapper = instance(),
            addressMapper = instance()
        )
    }

    bind<ProfileInsuranceRepository>() with singleton {
        ProfileInsuranceRepositoryImpl(
            instance(),
            instance()
        )
    }

    bind<PractitionerRepository>() with singleton {
        PractitionerRepositoryImpl(
            context = instance(),
            practitionerDataSource = instance(),
            languageMapper = instance(),
            bioMapper = instance(),
            medicalDegreeMapper = instance(),
            specialityMapper = instance()
        )
    }
    bind<PractitionerInsuranceRepository>() with singleton {
        PractitionerInsuranceRepositoryImpl(instance(),instance())
    }
}
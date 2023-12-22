package com.aya.digital.core.domain.prescriptions.di

import com.aya.digital.core.domain.prescriptions.base.SubscribeToPrescriptionsUseCase
import com.aya.digital.core.domain.prescriptions.base.impl.SubscribeToPrescriptionsUseCaseImpl
import com.aya.digital.core.domain.prescriptions.list.GetPrescriptionsByActorIdUseCase
import com.aya.digital.core.domain.prescriptions.list.GetPrescriptionsUseCase
import com.aya.digital.core.domain.prescriptions.list.impl.GetDoctorPrescriptionsByPractitionerIdUseCaseImpl
import com.aya.digital.core.domain.prescriptions.list.impl.GetPatientPrescriptionsByPatientIdUseCaseImpl
import com.aya.digital.core.domain.prescriptions.list.impl.GetPrescriptionsUseCaseImpl
import com.aya.digital.core.navigation.AppFlavour
import com.aya.digital.core.navigation.Flavor
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun prescriptionsDomainDiModule() = DI.Module("prescriptionsDomainDiModule") {
    bind<SubscribeToPrescriptionsUseCase>() with singleton {
        SubscribeToPrescriptionsUseCaseImpl(
            instance(),
            instance()
        )
    }

    bind<GetPrescriptionsByActorIdUseCase>() with singleton {
        val flavour = instance<AppFlavour>()
        when (flavour.flavour) {
            is Flavor.Patient -> GetPatientPrescriptionsByPatientIdUseCaseImpl(
                instance(),
                instance()
            )

            is Flavor.Doctor -> GetDoctorPrescriptionsByPractitionerIdUseCaseImpl(instance(), instance())
            else -> GetPatientPrescriptionsByPatientIdUseCaseImpl(
                instance(),
                instance()
            )
        }
    }

    bind<GetPrescriptionsUseCase>() with singleton {
        GetPrescriptionsUseCaseImpl(
            instance(),
            instance()
        )
    }

}
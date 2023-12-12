package com.aya.digital.core.domain.appointment.di

import com.aya.digital.core.domain.appointment.base.CancelAppointmentByIdUseCase
import com.aya.digital.core.domain.appointment.base.GetAppointmentByIdUseCase
import com.aya.digital.core.domain.appointment.base.GetAppointmentByIdWithParticipantUseCase
import com.aya.digital.core.domain.appointment.base.GetAppointmentsUseCase
import com.aya.digital.core.domain.appointment.base.GetAppointmentsWithParticipantsByStartDayAndDaysUseCase
import com.aya.digital.core.domain.appointment.base.GetAppointmentsWithParticipantsUseCase
import com.aya.digital.core.domain.appointment.base.impl.CancelAppointmentByIdUseCaseImpl
import com.aya.digital.core.domain.appointment.base.impl.GetAppointmentByIdUseCaseImpl
import com.aya.digital.core.domain.appointment.base.impl.GetAppointmentByIdWithParticipantUseCaseImpl
import com.aya.digital.core.domain.appointment.base.impl.GetAppointmentsUseCaseImpl
import com.aya.digital.core.domain.appointment.base.impl.GetAppointmentsWithParticipantUseCaseImpl
import com.aya.digital.core.domain.appointment.base.impl.GetAppointmentsWithParticipantsByStartDayAndDaysUseCaseImpl
import com.aya.digital.core.domain.appointment.create.CreateAppointmentUseCase
import com.aya.digital.core.domain.appointment.create.impl.CreateAppointmentUseCaseImpl
import com.aya.digital.core.domain.appointment.participants.GetParticipantByIdUseCase
import com.aya.digital.core.domain.appointment.participants.impl.GetDoctorParticipantByIdUseCaseImpl
import com.aya.digital.core.domain.appointment.participants.impl.GetPatientParticipantByIdUseCaseImpl
import com.aya.digital.core.domain.appointment.telehealth.GetTeleHealthRoomTokenUseCase
import com.aya.digital.core.domain.appointment.telehealth.GetTeleHealthTimeWindowUseCase
import com.aya.digital.core.domain.appointment.telehealth.impl.GetTeleHealthRoomTokenUseCaseImpl
import com.aya.digital.core.domain.appointment.telehealth.impl.GetTeleHealthTimeWindowUseCaseImpl
import com.aya.digital.core.navigation.AppFlavour
import com.aya.digital.core.navigation.Flavor
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun prescriptionsDomainDiModule() = DI.Module("prescriptionsDomainDiModule") {
    bind<GetAppointmentsUseCase>() with singleton {
        GetAppointmentsUseCaseImpl(
            instance(),
            instance()
        )
    }
    bind<GetAppointmentByIdUseCase>() with singleton {
        GetAppointmentByIdUseCaseImpl(
            instance(),
            instance()
        )
    }
    bind<CreateAppointmentUseCase>() with singleton {
        CreateAppointmentUseCaseImpl(
            instance(),
            instance()
        )
    }
    bind<GetTeleHealthRoomTokenUseCase>() with singleton {
        GetTeleHealthRoomTokenUseCaseImpl(
            instance(),
            instance()
        )
    }

    bind<GetTeleHealthTimeWindowUseCase>() with singleton {
        GetTeleHealthTimeWindowUseCaseImpl(instance(), instance())
    }

    bind<GetAppointmentByIdWithParticipantUseCase>() with singleton {
        GetAppointmentByIdWithParticipantUseCaseImpl(
            instance(),
            instance(),
            instance()
        )
    }

    bind<GetAppointmentsWithParticipantsUseCase>() with singleton {
        GetAppointmentsWithParticipantUseCaseImpl(
            instance(),
            instance(),
            instance()
        )
    }

    bind<GetAppointmentsWithParticipantsByStartDayAndDaysUseCase>() with singleton {
        GetAppointmentsWithParticipantsByStartDayAndDaysUseCaseImpl(
            instance()
        )
    }

    bind<GetParticipantByIdUseCase>() with singleton {
        val flavour = instance<AppFlavour>()
        when (flavour.flavour) {
            is Flavor.Patient -> GetDoctorParticipantByIdUseCaseImpl(
                instance(),
                instance()
            )

            is Flavor.Doctor -> GetPatientParticipantByIdUseCaseImpl(instance(), instance())
            else -> GetDoctorParticipantByIdUseCaseImpl(
                instance(),
                instance()
            )
        }
    }

    bind<CancelAppointmentByIdUseCase>() with singleton {
        CancelAppointmentByIdUseCaseImpl(
            instance(),
            instance()
        )
    }

}
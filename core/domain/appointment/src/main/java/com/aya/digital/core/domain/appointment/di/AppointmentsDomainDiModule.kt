package com.aya.digital.core.domain.appointment

import com.aya.digital.core.domain.appointment.base.GetAppointmentsUseCase
import com.aya.digital.core.domain.appointment.base.impl.GetAppointmentsUseCaseImpl
import com.aya.digital.core.domain.appointment.telehealth.GetTeleHealthRoomTokenUseCase
import com.aya.digital.core.domain.appointment.telehealth.impl.GetTeleHealthRoomTokenUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun appointmentsDomainDiModule() = DI.Module("appointmentsDomainDiModule") {
    bind<GetAppointmentsUseCase>() with singleton { GetAppointmentsUseCaseImpl(instance()) }
    bind<GetTeleHealthRoomTokenUseCase>() with singleton { GetTeleHealthRoomTokenUseCaseImpl(instance()) }

}
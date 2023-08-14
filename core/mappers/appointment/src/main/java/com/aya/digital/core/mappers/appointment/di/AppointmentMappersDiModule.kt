package com.aya.digital.core.mappers.appointment.di


import com.aya.digital.core.data.appointment.mappers.AppointmentMapper
import com.aya.digital.core.data.appointment.mappers.TelehealthWaitTimeMapper
import com.aya.digital.core.mappers.appointment.AppointmentMapperImpl
import com.aya.digital.core.mappers.appointment.TelehealthWaitTimeMapperImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton


fun appointmentMappersDiModule() = DI.Module("appointmentMappersDiModule") {
    bind<AppointmentMapper>() with singleton { AppointmentMapperImpl() }
    bind<TelehealthWaitTimeMapper>() with singleton { TelehealthWaitTimeMapperImpl() }
}
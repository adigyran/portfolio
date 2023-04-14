package com.aya.digital.core.di.modules

import com.aya.digital.core.mappers.appointment.di.appointmentMappersDiModule
import com.aya.digital.core.mappers.dictionaries.di.dictionariesMappersDiModule
import com.aya.digital.core.mappers.doctors.di.doctorMappersDiModule
import com.aya.digital.core.mappers.profile.di.profileMappersDiModule
import com.aya.digital.core.mappers.schedule.scheduleMappersDiModule

fun mappersDiModules() = listOf(
    profileMappersDiModule(),
    dictionariesMappersDiModule(),
    appointmentMappersDiModule(),
    doctorMappersDiModule(),
    scheduleMappersDiModule()
)
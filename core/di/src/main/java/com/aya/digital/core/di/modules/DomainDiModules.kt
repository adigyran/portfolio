package com.aya.digital.core.di.modules

import com.aya.digital.core.domain.appointment.di.appointmentsDomainDiModule
import com.aya.digital.core.domain.auth.di.authDomainDiModule
import com.aya.digital.core.domain.dictionaries.di.dictionariesDomainDiModule
import com.aya.digital.core.domain.doctors.di.doctorsDomainDiModule
import com.aya.digital.core.domain.schedule.di.scheduleDomainDiModule
import com.aya.digital.core.domain.profile.di.profileDomainDiModule
import com.aya.digital.core.domain.profile.di.rootDomainDiModule

fun domainDiModules() = listOf(
    authDomainDiModule(),
    profileDomainDiModule(),
    dictionariesDomainDiModule(),
    doctorsDomainDiModule(),
    appointmentsDomainDiModule(),
    scheduleDomainDiModule(),
    rootDomainDiModule()
)
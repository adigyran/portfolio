package com.aya.digital.core.di.modules

import com.aya.digital.core.datastore.di.dataStoreDiModule
import com.aya.digital.core.domain.appointment.appointmentsDomainDiModule
import com.aya.digital.core.domain.auth.di.authDomainDiModule
import com.aya.digital.core.domain.dictionaries.di.dictionariesDomainDiModule
import com.aya.digital.core.domain.doctors.doctorsDomainDiModule
import com.aya.digital.core.domain.profile.di.profileDomainDiModule

fun domainDiModules() = listOf(
    authDomainDiModule(),
    profileDomainDiModule(),
    dictionariesDomainDiModule(),
    doctorsDomainDiModule(),
    appointmentsDomainDiModule()
)
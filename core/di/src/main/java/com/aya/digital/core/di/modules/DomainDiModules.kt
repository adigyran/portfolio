package com.aya.digital.core.di.modules

import com.aya.digital.core.domain.appointment.di.appointmentsDomainDiModule
import com.aya.digital.core.domain.prescriptions.di.prescriptionsDomainDiModule
import com.aya.digital.core.domain.auth.di.authDomainDiModule
import com.aya.digital.core.domain.dictionaries.di.dictionariesDomainDiModule
import com.aya.digital.core.domain.location.di.locationDomainDiModule
import com.aya.digital.core.domain.doctors.di.doctorsDomainDiModule
import com.aya.digital.core.domain.home.di.homeDomainDiModule
import com.aya.digital.core.domain.profile.di.profileDomainDiModule
import com.aya.digital.core.domain.schedule.di.scheduleDomainDiModule
import com.aya.digital.core.domain.root.di.rootDomainDiModule

fun domainDiModules() = listOf(
    authDomainDiModule(),
    doctorsDomainDiModule(),
    dictionariesDomainDiModule(),
    appointmentsDomainDiModule(),
    profileDomainDiModule(),
    scheduleDomainDiModule(),
    prescriptionsDomainDiModule(),
    rootDomainDiModule(),
    homeDomainDiModule(),
    locationDomainDiModule()
)


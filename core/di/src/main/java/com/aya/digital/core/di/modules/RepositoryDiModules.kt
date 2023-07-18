package com.aya.digital.core.di.modules

import com.aya.digital.core.repository.appointment.appointmentRepositoryDiModule
import com.aya.digital.core.repository.auth.authRepositoryDiModule
import com.aya.digital.core.repository.auth.dictionariesRepositoryDiModule
import com.aya.digital.core.repository.doctors.doctorsRepositoryDiModule
import com.aya.digital.core.repository.doctors.scheduleRepositoryDiModule
import com.aya.digital.core.repository.location.locationRepositoryDiModule
import com.aya.digital.core.repository.profile.profileRepositoryDiModule
import com.aya.digital.core.repository.progress.progressRepositoryDiModule

fun repositoryDiModules() = listOf(
    authRepositoryDiModule(),
    profileRepositoryDiModule(),
    dictionariesRepositoryDiModule(),
    doctorsRepositoryDiModule(),
    appointmentRepositoryDiModule(),
    scheduleRepositoryDiModule(),
    progressRepositoryDiModule(),
    locationRepositoryDiModule()
)
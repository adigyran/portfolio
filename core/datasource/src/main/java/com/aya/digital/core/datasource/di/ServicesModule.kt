package com.aya.digital.core.datasource

import com.aya.digital.core.datasource.network.*

import org.kodein.di.DI

fun servicesModule() = DI.Module("servicesModule") {
    importOnce(appointmentNetworkModule())
    importOnce(practitionersNetworkModule())
    importOnce(profileNetworkModule())
    importOnce(profilePractitionerModule())
    importOnce(profilePatientModule())
    importOnce(scheduleNetworkModule())
}

package com.aya.digital.core.network.di

import com.aya.digital.core.datasources.network.*

import org.kodein.di.DI

fun servicesModule() = DI.Module("servicesModule") {
    importOnce(appointmentNetworkModule())
    importOnce(practitionersNetworkModule())
    importOnce(profileNetworkModule())
    importOnce(profilePractitionerModule())
    importOnce(profilePatientModule())
    importOnce(scheduleNetworkModule())
}

package com.aya.digital.core.datasource.di

import com.aya.digital.core.datasource.network.*

import org.kodein.di.DI

fun servicesModule() = DI.Module("servicesModule") {
    importOnce(authNetworkModule())
    importOnce(dictionariesNetworkModule())
    importOnce(appointmentNetworkModule())
    importOnce(practitionersNetworkModule())
    importOnce(profileNetworkModule())
    importOnce(profilePractitionerModule())
    importOnce(profilePatientModule())
    importOnce(scheduleNetworkModule())
    importOnce(telehealthNetworkModule())
    importOnce(prescriptionNetworkModule())
    importOnce(tokenNetworkModule())
    importOnce(geoCodingApiNetwork())
}

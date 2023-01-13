package com.aya.digital.core.network.di

import com.aya.digital.core.network.api.services.AppointmentService
import com.aya.digital.core.network.api.services.PractitionersService
import com.aya.digital.core.network.datasources.AppointmentDataSource
import com.aya.digital.core.network.datasources.PractitionersDataSource
import com.aya.digital.core.network.di.services.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.eagerSingleton
import org.kodein.di.instance

fun servicesModule() = DI.Module("servicesModule") {
    importOnce(appointmentNetworkModule())
    importOnce(practitionersNetworkModule())
    importOnce(profileNetworkModule())
    importOnce(profilePractitionerModule())
    importOnce(profilePatientModule())
    importOnce(scheduleNetworkModule())
}

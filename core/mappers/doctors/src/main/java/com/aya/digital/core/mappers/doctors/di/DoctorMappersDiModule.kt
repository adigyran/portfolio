package com.aya.digital.core.mappers.doctors.di


import com.aya.digital.core.data.doctors.mappers.*
import com.aya.digital.core.mappers.doctors.*
import com.aya.digital.core.mappers.doctors.ClinicMapperImpl
import com.aya.digital.core.mappers.doctors.InsuranceMapperImpl
import com.aya.digital.core.mappers.doctors.LocationMapperImpl
import com.aya.digital.core.mappers.doctors.SpecialityMapperImpl
import com.aya.digital.core.mappers.profile.*

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton


fun doctorMappersDiModule() = DI.Module("doctorMappersDiModule") {
    bind<InsuranceMapper>() with singleton { InsuranceMapperImpl() }
    bind<ClinicMapper>() with singleton { ClinicMapperImpl() }
    bind<LocationMapper>() with singleton { LocationMapperImpl() }
    bind<SpecialityMapper>() with singleton { SpecialityMapperImpl() }
    bind<DoctorDataMapper>() with singleton { DoctorDataMapperImpl(instance(),instance(),instance(),instance()) }

}
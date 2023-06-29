package com.aya.digital.core.domain.doctors

import com.aya.digital.core.domain.doctors.base.GetDoctorByIdUseCase
import com.aya.digital.core.domain.doctors.base.impl.GetDoctorsUseCaseImpl
import com.aya.digital.core.domain.doctors.base.impl.GetDoctorByIdUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun doctorsDomainDiModule() = DI.Module("doctorsDomainDiModule") {
    bind<GetDoctorByIdUseCase>() with singleton { GetDoctorByIdUseCaseImpl(instance(),instance()) }
    bind<GetDoctorsUseCaseImpl>() with singleton { GetDoctorsUseCaseImpl(instance(),instance()) }

}
package com.aya.digital.core.domain.doctors

import com.aya.digital.core.domain.doctors.base.GetDoctorByIdUseCase
import com.aya.digital.core.domain.doctors.base.impl.GetDoctorsUseCaseImpl
import com.aya.digital.core.domain.doctors.base.impl.GetDoctorByIdUseCaseImpl
import com.aya.digital.core.domain.doctors.favourites.AddDoctorToFavoritesUseCase
import com.aya.digital.core.domain.doctors.favourites.CheckDoctorIsInFavoritesUseCase
import com.aya.digital.core.domain.doctors.favourites.GetFavoriteDoctorsUseCase
import com.aya.digital.core.domain.doctors.favourites.RemoveDoctorFromFavoritesUseCase
import com.aya.digital.core.domain.doctors.favourites.impl.AddDoctorToFavoritesUseCaseImpl
import com.aya.digital.core.domain.doctors.favourites.impl.CheckDoctorIsInFavoritesUseCaseImpl
import com.aya.digital.core.domain.doctors.favourites.impl.GetFavoriteDoctorsUseCaseImpl
import com.aya.digital.core.domain.doctors.favourites.impl.RemoveDoctorFromFavoritesUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun doctorsDomainDiModule() = DI.Module("doctorsDomainDiModule") {
    bind<GetDoctorByIdUseCase>() with singleton { GetDoctorByIdUseCaseImpl(instance(),instance()) }
    bind<GetDoctorsUseCaseImpl>() with singleton { GetDoctorsUseCaseImpl(instance(),instance()) }

    //favorites
    bind<AddDoctorToFavoritesUseCase>() with singleton { AddDoctorToFavoritesUseCaseImpl(instance(),instance()) }
    bind<CheckDoctorIsInFavoritesUseCase>() with singleton { CheckDoctorIsInFavoritesUseCaseImpl(instance(),instance()) }
    bind<GetFavoriteDoctorsUseCase>() with singleton { GetFavoriteDoctorsUseCaseImpl(instance(),instance()) }
    bind<RemoveDoctorFromFavoritesUseCase>() with singleton { RemoveDoctorFromFavoritesUseCaseImpl(instance(),instance()) }
}
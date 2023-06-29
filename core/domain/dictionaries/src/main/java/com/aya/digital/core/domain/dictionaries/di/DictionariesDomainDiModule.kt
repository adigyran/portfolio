package com.aya.digital.core.domain.dictionaries.di

import com.aya.digital.core.domain.dictionaries.insurancecompany.GetInsuranceCompanyItemsUseCase
import com.aya.digital.core.domain.dictionaries.base.GetMultiSelectItemsUseCase
import com.aya.digital.core.domain.dictionaries.base.impl.GetMultiSelectItemsUseCaseImpl
import com.aya.digital.core.domain.dictionaries.cities.GetCityItemsUseCase
import com.aya.digital.core.domain.dictionaries.cities.impl.GetCityItemsUseCaseImpl
import com.aya.digital.core.domain.dictionaries.insurancecompany.GetInsuranceCompanyItemByIdUseCase
import com.aya.digital.core.domain.dictionaries.insurancecompany.impl.GetInsuranceCompanyItemByIdUseCaseImpl
import com.aya.digital.core.domain.dictionaries.insurancecompany.impl.GetInsuranceCompanyItemsUseCaseImpl
import com.aya.digital.core.domain.dictionaries.speciality.GetSpecialityItemsUseCase
import com.aya.digital.core.domain.dictionaries.speciality.impl.GetSpecialityItemsUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun dictionariesDomainDiModule() = DI.Module("dictionariesDomainDiModule") {
    bind<GetInsuranceCompanyItemsUseCase>() with singleton { GetInsuranceCompanyItemsUseCaseImpl(instance()) }
    bind<GetInsuranceCompanyItemByIdUseCase>() with singleton { GetInsuranceCompanyItemByIdUseCaseImpl(instance()) }

    bind<GetSpecialityItemsUseCase>() with singleton { GetSpecialityItemsUseCaseImpl(instance()) }

    bind<GetCityItemsUseCase>() with singleton { GetCityItemsUseCaseImpl(instance()) }

    bind<GetMultiSelectItemsUseCase>() with singleton { GetMultiSelectItemsUseCaseImpl(instance(), instance(), instance()) }


}
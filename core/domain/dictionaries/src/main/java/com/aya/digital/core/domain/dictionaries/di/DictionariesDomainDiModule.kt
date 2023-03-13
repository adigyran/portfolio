package com.aya.digital.core.domain.dictionaries.di

import com.aya.digital.core.domain.dictionaries.insurancecompany.GetInsuranceCompanyItemsUseCase
import com.aya.digital.core.domain.dictionaries.base.GetMultiSelectItemsUseCase
import com.aya.digital.core.domain.dictionaries.base.impl.GetMultiSelectItemsUseCaseImpl
import com.aya.digital.core.domain.dictionaries.insurancecompany.GetInsuranceCompanyItemByIdUseCase
import com.aya.digital.core.domain.dictionaries.insurancecompany.impl.GetInsuranceCompanyItemByIdUseCaseImpl
import com.aya.digital.core.domain.dictionaries.insurancecompany.impl.GetInsuranceCompanyItemsUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun dictionariesDomainDiModule() = DI.Module("dictionariesDomainDiModule") {
    bind<GetInsuranceCompanyItemsUseCase>() with singleton { GetInsuranceCompanyItemsUseCaseImpl(instance()) }
    bind<GetInsuranceCompanyItemByIdUseCase>() with singleton { GetInsuranceCompanyItemByIdUseCaseImpl(instance()) }

    bind<GetMultiSelectItemsUseCase>() with singleton { GetMultiSelectItemsUseCaseImpl(instance()) }


}
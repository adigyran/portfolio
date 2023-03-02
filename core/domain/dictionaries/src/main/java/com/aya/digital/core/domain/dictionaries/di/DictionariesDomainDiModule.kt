package com.aya.digital.core.domain.dictionaries.di

import com.aya.digital.core.domain.dictionaries.GetInsuranceItemsUseCase
import com.aya.digital.core.domain.dictionaries.GetInsuranceItemsUseCaseImpl
import com.aya.digital.core.domain.dictionaries.GetMultiSelectItemsUseCase
import com.aya.digital.core.domain.dictionaries.GetMultiSelectItemsUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun dictionariesDomainDiModule() = DI.Module("dictionariesDomainDiModule") {
/*    bind<SignInUseCase>() with singleton { SignInUseCaseImpl(instance()) }
    bind<SignUpUseCase>() with singleton { SignUpUseCaseImpl(instance()) }*/
    bind<GetInsuranceItemsUseCase>() with singleton { GetInsuranceItemsUseCaseImpl(instance()) }
    bind<GetMultiSelectItemsUseCase>() with singleton { GetMultiSelectItemsUseCaseImpl(instance()) }


}
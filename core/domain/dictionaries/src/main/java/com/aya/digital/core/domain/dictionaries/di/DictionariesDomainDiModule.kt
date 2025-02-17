package com.aya.digital.core.domain.dictionaries.di

import com.aya.digital.core.domain.dictionaries.insurancecompany.GetInsuranceCompanyItemsUseCase
import com.aya.digital.core.domain.dictionaries.base.GetMultiSelectItemsUseCase
import com.aya.digital.core.domain.dictionaries.base.impl.GetMultiSelectItemsUseCaseImpl
import com.aya.digital.core.domain.dictionaries.cities.GetCityItemsUseCase
import com.aya.digital.core.domain.dictionaries.cities.impl.GetCityItemsUseCaseImpl
import com.aya.digital.core.domain.dictionaries.emergencycontacttype.GetEmergencyContactTypeItemByIdUseCase
import com.aya.digital.core.domain.dictionaries.emergencycontacttype.GetEmergencyContactTypeItemsUseCase
import com.aya.digital.core.domain.dictionaries.emergencycontacttype.impl.GetEmergencyContactTypeItemByIdUseCaseImpl
import com.aya.digital.core.domain.dictionaries.emergencycontacttype.impl.GetEmergencyContactTypetemsUseCaseImpl
import com.aya.digital.core.domain.dictionaries.languages.GetLanguageItemsUseCase
import com.aya.digital.core.domain.dictionaries.languages.impl.GetLanguageItemsUseCaseImpl
import com.aya.digital.core.domain.dictionaries.insurancecompany.GetInsuranceCompanyItemByIdUseCase
import com.aya.digital.core.domain.dictionaries.insurancecompany.impl.GetInsuranceCompanyItemByIdUseCaseImpl
import com.aya.digital.core.domain.dictionaries.insurancecompany.impl.GetInsuranceCompanyItemsUseCaseImpl
import com.aya.digital.core.domain.dictionaries.medicaldegrees.GetMedicalDegreeItemsUseCase
import com.aya.digital.core.domain.dictionaries.medicaldegrees.impl.GetMedicalDegreeItemsUseCaseImpl
import com.aya.digital.core.domain.dictionaries.speciality.GetSpecialityItemsUseCase
import com.aya.digital.core.domain.dictionaries.speciality.impl.GetSpecialityItemsUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun dictionariesDomainDiModule() = DI.Module("dictionariesDomainDiModule") {
    bind<GetInsuranceCompanyItemsUseCase>() with singleton {
        GetInsuranceCompanyItemsUseCaseImpl(
            instance(),
            instance()
        )
    }
    bind<GetInsuranceCompanyItemByIdUseCase>() with singleton {
        GetInsuranceCompanyItemByIdUseCaseImpl(
            instance(),
            instance()
        )
    }

    bind<GetSpecialityItemsUseCase>() with singleton {
        GetSpecialityItemsUseCaseImpl(
            instance(),
            instance()
        )
    }

    bind<GetMedicalDegreeItemsUseCase>() with singleton {
        GetMedicalDegreeItemsUseCaseImpl(
            instance(),
            instance()
        )
    }

    bind<GetEmergencyContactTypeItemsUseCase>() with singleton {
        GetEmergencyContactTypetemsUseCaseImpl(
            instance(),
            instance()
        )
    }

    bind<GetEmergencyContactTypeItemByIdUseCase>() with singleton {
        GetEmergencyContactTypeItemByIdUseCaseImpl(
            instance(),
            instance()
        )
    }

    bind<GetCityItemsUseCase>() with singleton { GetCityItemsUseCaseImpl(instance(), instance()) }


    bind<GetLanguageItemsUseCase>() with singleton {
        GetLanguageItemsUseCaseImpl(
            instance(),
            instance()
        )
    }

    bind<GetMultiSelectItemsUseCase>() with singleton {
        GetMultiSelectItemsUseCaseImpl(
            getInsuranceCompanyItemsUseCase = instance(),
            getSpecialityItemsUseCase = instance(),
            getCityItemsUseCase = instance(),
            getLanguageItemsUseCase = instance(),
            getMedicalDegreeItemsUseCase = instance(),
            getEmergencyContactTypeItemsUseCase = instance()
        )
    }
}

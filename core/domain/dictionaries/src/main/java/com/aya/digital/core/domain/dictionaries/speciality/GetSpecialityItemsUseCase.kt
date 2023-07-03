package com.aya.digital.core.domain.dictionaries.speciality

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.dictionaries.SpecialityModel
import com.aya.digital.core.domain.dictionaries.base.model.MultiSelectItem
import com.aya.digital.core.domain.dictionaries.insurancecompany.model.InsuranceCompanyItem
import com.aya.digital.core.domain.dictionaries.speciality.model.SpecialityItem
import com.aya.digital.core.domain.dictionaries.speciality.model.SpecialityItemPaginationModel
import io.reactivex.rxjava3.core.Flowable

interface GetSpecialityItemsUseCase {
    operator fun invoke(
        searchTerm: String?,
        cursor: String?
    ): Flowable<RequestResultModel<SpecialityItemPaginationModel>>
}
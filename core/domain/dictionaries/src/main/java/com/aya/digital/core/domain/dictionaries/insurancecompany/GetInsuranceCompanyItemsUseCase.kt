package com.aya.digital.core.domain.dictionaries.insurancecompany

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.dictionaries.base.model.MultiSelectItem
import com.aya.digital.core.domain.dictionaries.insurancecompany.model.InsuranceCompanyItem
import com.aya.digital.core.domain.dictionaries.insurancecompany.model.InsuranceCompanyItemPaginationModel
import io.reactivex.rxjava3.core.Flowable

interface GetInsuranceCompanyItemsUseCase {
    operator fun invoke(searchTerm:String?,selectedItems:Set<Int>, cursor: String?): Flowable<RequestResultModel<InsuranceCompanyItemPaginationModel>>
}
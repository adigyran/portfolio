package com.aya.digital.core.domain.dictionaries.insurancecompany

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.dictionaries.base.model.MultiSelectItem
import com.aya.digital.core.domain.dictionaries.insurancecompany.model.InsuranceCompanyItem
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface GetInsuranceCompanyItemByIdUseCase {
    operator fun invoke(companyId:Int): Single<RequestResultModel<InsuranceCompanyItem>>
}
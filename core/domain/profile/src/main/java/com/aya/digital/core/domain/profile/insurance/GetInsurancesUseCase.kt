package com.aya.digital.core.domain.profile.insurance

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.insurance.model.InsurancePolicyItemModel
import io.reactivex.rxjava3.core.Observable

interface GetInsurancesUseCase {
    operator fun invoke(): Observable<RequestResultModel<List<InsurancePolicyItemModel>>>
}


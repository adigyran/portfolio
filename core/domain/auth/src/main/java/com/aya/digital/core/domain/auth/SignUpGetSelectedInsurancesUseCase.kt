package com.aya.digital.core.domain.auth

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.auth.model.InsuranceModel
import io.reactivex.rxjava3.core.Observable

interface SignUpGetSelectedInsurancesUseCase {
    operator fun invoke(
        selectedInsurancesIds: List<Int>
    ): Observable<RequestResultModel<Set<InsuranceModel>>>
}


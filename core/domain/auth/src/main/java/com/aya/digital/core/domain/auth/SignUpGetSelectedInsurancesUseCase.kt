package com.aya.digital.core.domain.auth

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import io.reactivex.rxjava3.core.Observable

interface SignUpGetSelectedInsurancesUseCase {
    operator fun invoke(
        selectedInsurancesIds:List<Int>
    ): Observable<RequestResultModel<Set<InsuranceModel>>>
}

data class InsuranceModel(
    val id: Int,
    val name: String
    )


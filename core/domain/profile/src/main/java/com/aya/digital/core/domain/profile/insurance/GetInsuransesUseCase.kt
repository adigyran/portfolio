package com.aya.digital.core.domain.profile.insurance

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.insurance.model.InsuranceModel
import io.reactivex.rxjava3.core.Single

interface GetInsuransesUseCase {
    operator fun invoke(): Single<RequestResultModel<List<InsuranceModel>>>
}


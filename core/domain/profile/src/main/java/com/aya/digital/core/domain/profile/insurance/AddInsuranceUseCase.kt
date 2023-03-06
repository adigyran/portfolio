package com.aya.digital.core.domain.profile.insurance

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.insurance.model.InsuranceAddModel
import io.reactivex.rxjava3.core.Single

interface AddInsuranceUseCase {
    operator fun invoke(addInsuranceModel : InsuranceAddModel): Single<RequestResultModel<Boolean>>
}




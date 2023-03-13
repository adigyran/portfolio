package com.aya.digital.core.domain.profile.insurance

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.insurance.model.InsuranceAddModel
import com.aya.digital.core.domain.profile.insurance.model.InsuranceSaveModel
import io.reactivex.rxjava3.core.Single

interface SaveInsuranceUseCase {
    operator fun invoke(saveModel: InsuranceSaveModel): Single<RequestResultModel<Boolean>>
}




package com.aya.digital.core.domain.profile.insurance

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.insurance.model.InsuranceAddModel
import io.reactivex.rxjava3.core.Single

class AddInsuranceUseCaseImpl : AddInsuranceUseCase {
    override fun invoke(addInsuranceModel: InsuranceAddModel): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }
}
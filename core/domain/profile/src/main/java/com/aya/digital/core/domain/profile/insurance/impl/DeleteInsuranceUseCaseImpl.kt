package com.aya.digital.core.domain.profile.insurance.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.insurance.DeleteInsuranceUseCase
import io.reactivex.rxjava3.core.Single

internal class DeleteInsuranceUseCaseImpl : DeleteInsuranceUseCase {
    override fun invoke(id: Int): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }
}
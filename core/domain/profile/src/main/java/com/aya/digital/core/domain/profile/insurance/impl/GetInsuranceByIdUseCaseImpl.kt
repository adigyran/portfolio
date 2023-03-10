package com.aya.digital.core.domain.profile.insurance.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.InsurancePolicyModel
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.domain.profile.insurance.GetInsuranceByIdUseCase
import com.aya.digital.core.domain.profile.insurance.model.InsurancePolicyItemModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

class GetInsuranceByIdUseCaseImpl(private val profileRepository: ProfileRepository) : GetInsuranceByIdUseCase {
    override fun invoke(id: Int): Single<RequestResultModel<InsurancePolicyModel>> =
        profileRepository.getInsuranceById(id)
            .mapResult({
                it.asResultModel()
            },{it.toModelError()})
}
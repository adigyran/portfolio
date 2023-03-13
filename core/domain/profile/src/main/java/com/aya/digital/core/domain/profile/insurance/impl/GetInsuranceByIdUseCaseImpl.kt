package com.aya.digital.core.domain.profile.insurance.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.dictionaries.repository.DictionariesRepository
import com.aya.digital.core.data.profile.InsurancePolicyModel
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.domain.profile.insurance.GetInsuranceByIdUseCase
import com.aya.digital.core.domain.profile.insurance.model.InsurancePolicyItemModel
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.flatMapResult
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

class GetInsuranceByIdUseCaseImpl(
    private val profileRepository: ProfileRepository,
    private val dictionariesRepository: DictionariesRepository
) : GetInsuranceByIdUseCase {
    override fun invoke(id: Int): Single<RequestResultModel<InsurancePolicyModel>> =
        profileRepository.getInsuranceById(id)
            .flatMapResult({ insurancePolicyModel ->
                dictionariesRepository.getInsuranceCompanyById(insurancePolicyModel.organisationId)
                    .mapResult({
                        insurancePolicyModel.organisationName = it.name
                        insurancePolicyModel.asResult()
                    }, { it })
            }, { Single.just(it) })
            .mapResult({
                it.asResultModel()
            }, { it.toModelError() })
}
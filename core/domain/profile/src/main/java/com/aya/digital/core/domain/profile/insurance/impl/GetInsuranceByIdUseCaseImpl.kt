package com.aya.digital.core.domain.profile.insurance.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.dictionaries.repository.DictionariesRepository
import com.aya.digital.core.data.profile.repository.ProfileInsuranceRepository
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.insurance.GetInsuranceByIdUseCase
import com.aya.digital.core.domain.profile.insurance.model.InsurancePolicyItemModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class GetInsuranceByIdUseCaseImpl(
    private val profileInsuranceRepository: ProfileInsuranceRepository,
    private val dictionariesRepository: DictionariesRepository,
    private val progressRepository: ProgressRepository
) : GetInsuranceByIdUseCase {
    override fun invoke(id: Int): Single<RequestResultModel<InsurancePolicyItemModel>> =
        profileInsuranceRepository.getInsuranceById(id)
            .trackProgress(progressRepository)
            .mapResult({insurancePolicyModel ->
                InsurancePolicyItemModel(
                    insurancePolicyModel.id,
                    insurancePolicyModel.organisationId,
                    insurancePolicyModel.organisationName,
                    insurancePolicyModel.number,
                    insurancePolicyModel.photoUrl
                ).asResultModel()
            }, { it.toModelError() })
}

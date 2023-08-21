package com.aya.digital.core.domain.profile.insurance.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.dictionaries.repository.DictionariesRepository
import com.aya.digital.core.data.profile.repository.ProfileInsuranceRepository
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.insurance.GetInsurancesUseCase
import com.aya.digital.core.domain.profile.insurance.model.InsurancePolicyItemModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Observable

internal class GetInsurancesUseCaseImpl(
    private val profileInsuranceRepository: ProfileInsuranceRepository,
    private val dictionariesRepository: DictionariesRepository,
    private val progressRepository: ProgressRepository
) : GetInsurancesUseCase {
    override fun invoke(): Observable<RequestResultModel<List<InsurancePolicyItemModel>>> =
        profileInsuranceRepository.getInsurances()
            .trackProgress(progressRepository)
            .mapResult({
                it.map { insurancePolicyModel ->
                    InsurancePolicyItemModel(
                        insurancePolicyModel.id,
                        insurancePolicyModel.organisationId,
                        insurancePolicyModel.organisationName,
                        insurancePolicyModel.number,
                        insurancePolicyModel.photoUrl
                    )
                }.asResultModel()
            },
                { it.toModelError() })

}
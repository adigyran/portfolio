package com.aya.digital.core.domain.dictionaries.insurancecompany.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.dictionaries.repository.DictionariesRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.dictionaries.insurancecompany.GetInsuranceCompanyItemByIdUseCase
import com.aya.digital.core.domain.dictionaries.insurancecompany.model.InsuranceCompanyItem
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

internal class GetInsuranceCompanyItemByIdUseCaseImpl(
    private val dictionariesRepository: DictionariesRepository,
    private val progressRepository: ProgressRepository
) : GetInsuranceCompanyItemByIdUseCase {
    override fun invoke(companyId: Int): Single<RequestResultModel<InsuranceCompanyItem>> =
        dictionariesRepository.getInsuranceCompanyById(companyId)
            .trackProgress(progressRepository)
            .mapResult({ InsuranceCompanyItem(it.id, it.name).asResultModel() },
                { it.toModelError() })
}
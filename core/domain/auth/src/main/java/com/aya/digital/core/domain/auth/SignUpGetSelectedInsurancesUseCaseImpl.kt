package com.aya.digital.core.domain.auth

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.DictionariesRepository
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Observable

internal class SignUpGetSelectedInsurancesUseCaseImpl(private val dictionariesRepository: DictionariesRepository) :
    SignUpGetSelectedInsurancesUseCase {
    override fun invoke(selectedInsurancesIds: List<Int>): Observable<RequestResultModel<Set<InsuranceModel>>> =
        dictionariesRepository.getInsurancesByIds(selectedInsurancesIds)
            .mapResult({ set ->
                set.map { InsuranceModel(it.id, it.name) }.toSet().asResultModel()
            }, { it.toModelError() })
}
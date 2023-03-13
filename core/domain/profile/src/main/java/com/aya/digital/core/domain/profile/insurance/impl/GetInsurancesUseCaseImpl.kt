package com.aya.digital.core.domain.profile.insurance.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.dictionaries.repository.DictionariesRepository
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.domain.profile.insurance.GetInsurancesUseCase
import com.aya.digital.core.domain.profile.insurance.model.InsurancePolicyItemModel
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.flatMapResult
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable

internal class GetInsurancesUseCaseImpl(
    private val profileRepository: ProfileRepository,
    private val dictionariesRepository: DictionariesRepository
) : GetInsurancesUseCase {
    override fun invoke(): Observable<RequestResultModel<List<InsurancePolicyItemModel>>> =
        profileRepository.getInsurances()
            .flatMapResult({ insurancePolicyModels ->
                Observable.fromIterable(insurancePolicyModels)
                    .flatMap { insurancePolicyModel ->
                        dictionariesRepository.getInsuranceCompanyById(insurancePolicyModel.organisationId)
                            .toObservable()
                            .mapResult({ insuranceCompanyModel ->
                                insurancePolicyModel.organisationName = insuranceCompanyModel.name
                                insurancePolicyModel.asResult()
                            }, { it })
                    }
            }, { Observable.just(it) })
            .mapResult({ listOf<InsurancePolicyItemModel>().asResultModel() },
                { it.toModelError() })

}
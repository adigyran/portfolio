package com.aya.digital.core.domain.profile.insurance.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.domain.profile.insurance.GetInsurancesUseCase
import com.aya.digital.core.domain.profile.insurance.model.InsurancePolicyItemModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Observable

internal class GetInsurancesUseCaseImpl(private val profileRepository: ProfileRepository) : GetInsurancesUseCase {
    override fun invoke(): Observable<RequestResultModel<List<InsurancePolicyItemModel>>> =
        profileRepository.getInsurances()
            .mapResult({ listOf<InsurancePolicyItemModel>().asResultModel() },{it.toModelError()})

}
package com.aya.digital.core.domain.profile.insurance.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.domain.profile.insurance.AddInsuranceUseCase
import com.aya.digital.core.domain.profile.insurance.model.InsuranceAddModel
import io.reactivex.rxjava3.core.Single

internal class AddInsuranceUseCaseImpl(private val profileRepository: ProfileRepository) : AddInsuranceUseCase {
    override fun invoke(addInsuranceModel: InsuranceAddModel): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }
}
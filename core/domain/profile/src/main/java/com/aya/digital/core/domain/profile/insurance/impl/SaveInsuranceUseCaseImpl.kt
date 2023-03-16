package com.aya.digital.core.domain.profile.insurance.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.domain.profile.insurance.SaveInsuranceUseCase
import com.aya.digital.core.domain.profile.insurance.model.InsuranceSaveModel
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.network.model.request.InsurancePolicyBody
import io.reactivex.rxjava3.core.Single

internal class SaveInsuranceUseCaseImpl(private val profileRepository: ProfileRepository) : SaveInsuranceUseCase {
    override fun invoke(saveModel: InsuranceSaveModel): Single<RequestResultModel<Boolean>> =
        profileRepository.saveInsurance(saveModel.id, InsurancePolicyBody(saveModel.number,saveModel.name))
            .mapResult({it.asResultModel()},{it.toModelError()})
}
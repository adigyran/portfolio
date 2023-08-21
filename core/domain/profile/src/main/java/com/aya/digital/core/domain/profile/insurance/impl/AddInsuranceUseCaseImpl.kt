package com.aya.digital.core.domain.profile.insurance.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileInsuranceRepository
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.insurance.AddInsuranceUseCase
import com.aya.digital.core.domain.profile.insurance.model.InsuranceAddModel
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.network.model.request.InsurancePolicyBody
import io.reactivex.rxjava3.core.Single

internal class AddInsuranceUseCaseImpl(private val profileInsuranceRepository:ProfileInsuranceRepository,
                                       private val progressRepository: ProgressRepository
) : AddInsuranceUseCase {
    override fun invoke(addInsuranceModel: InsuranceAddModel): Single<RequestResultModel<Boolean>> =
        profileInsuranceRepository.addInsurance(InsurancePolicyBody(addInsuranceModel.number,addInsuranceModel.name,addInsuranceModel.photo))
            .trackProgress(progressRepository)
            .mapResult({it.asResultModel()},{it.toModelError()})
}
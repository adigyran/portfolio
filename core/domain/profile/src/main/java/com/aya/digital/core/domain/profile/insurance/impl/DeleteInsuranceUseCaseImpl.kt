package com.aya.digital.core.domain.profile.insurance.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileInsuranceRepository
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.insurance.DeleteInsuranceUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class DeleteInsuranceUseCaseImpl(private val profileInsuranceRepository: ProfileInsuranceRepository,
                                          private val progressRepository: ProgressRepository
) : DeleteInsuranceUseCase {
    override fun invoke(id: Int): Single<RequestResultModel<Boolean>> =
        profileInsuranceRepository.deleteInsurance(id)
            .trackProgress(progressRepository)
            .mapResult({it.asResultModel()},{it.toModelError()})
}
package com.aya.digital.core.domain.profile.insurance.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.PractitionerInsuranceRepository
import com.aya.digital.core.data.profile.repository.PractitionerRepository
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.insurance.GetDoctorInsurancesUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Observable

internal class GetDoctorInsurancesUseCaseImpl(private val practitionerInsuranceRepository: PractitionerInsuranceRepository,
                                              private val progressRepository: ProgressRepository
) : GetDoctorInsurancesUseCase {
    override fun invoke(): Observable<RequestResultModel<List<Int>>> =
        practitionerInsuranceRepository.getPractitionerInsurances()
            .trackProgress(progressRepository)
            .mapResult({it.asResultModel()},{it.toModelError()})
}
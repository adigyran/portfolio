package com.aya.digital.core.domain.profile.insurance.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.PractitionerInsuranceRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.insurance.UpdateDoctorInsurancesUseCase
import com.aya.digital.core.ext.flatMapResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import  com.aya.digital.core.ext.intersect
import timber.log.Timber

internal class UpdateDoctorInsurancesUseCaseImpl(
    private val progressRepository: ProgressRepository,
    private val practitionerInsuranceRepository: PractitionerInsuranceRepository
) : UpdateDoctorInsurancesUseCase {
    override fun invoke(ids: List<Int>): Observable<RequestResultModel<List<Int>>> =
        practitionerInsuranceRepository.getPractitionerInsurances()
            .trackProgress(progressRepository)
            .flatMapResult({serverIds ->
                val addDelta = ids.subtract(serverIds)
                val removeDelta = serverIds.subtract(ids)
                Timber.d("$addDelta $removeDelta")
                Observable.just(addDelta.toList().asResultModel())
            },{ Observable.just(it.toModelError())})
}
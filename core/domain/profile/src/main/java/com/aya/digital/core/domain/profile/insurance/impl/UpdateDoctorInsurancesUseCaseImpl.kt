package com.aya.digital.core.domain.profile.insurance.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.PractitionerInsuranceRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.insurance.UpdateDoctorInsurancesUseCase
import com.aya.digital.core.ext.flatMapResult
import com.aya.digital.core.ext.intersect
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import com.aya.digital.core.ext.subtract as intersect1
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.ext.subtract
import io.reactivex.rxjava3.functions.BiFunction
import timber.log.Timber

internal class UpdateDoctorInsurancesUseCaseImpl(
    private val progressRepository: ProgressRepository,
    private val practitionerInsuranceRepository: PractitionerInsuranceRepository
) : UpdateDoctorInsurancesUseCase {
    override fun invoke(ids: Set<Int>): Observable<RequestResultModel<Set<Int>>> =
       getInsurances()
            .flatMapResult({serverIds ->
                val addDelta = ids.subtract(serverIds)
                val removeDelta = serverIds.subtract(ids)
                Timber.d("INTERSECT $addDelta $removeDelta")
                Single.zip(
                    addInsurances(addDelta),
                    removeInsurances(ids),
                    BiFunction { t1, t2 -> })
                    .flatMapObservable {
                       // getInsurances().mapResult({it.asResultModel()},{it.toModelError()})
                        Observable.just(setOf<Int>().asResultModel())
                    }

            },{ Observable.just(it.toModelError())})

    private fun getInsurances() = practitionerInsuranceRepository.getPractitionerInsurances().trackProgress(progressRepository)

    private fun addInsurances(ids: Set<Int>) = practitionerInsuranceRepository.addInsurances(ids).trackProgress(progressRepository)

    private fun removeInsurances(ids: Set<Int>) = practitionerInsuranceRepository.deleteInsurances(ids).trackProgress(progressRepository)
}
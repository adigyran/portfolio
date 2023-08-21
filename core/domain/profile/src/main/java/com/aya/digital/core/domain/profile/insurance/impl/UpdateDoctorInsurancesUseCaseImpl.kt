package com.aya.digital.core.domain.profile.insurance.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.PractitionerInsuranceRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.insurance.UpdateDoctorInsurancesUseCase
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.flatMapResult
import com.aya.digital.core.ext.intersect
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import com.aya.digital.core.ext.subtract as intersect1
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.ext.subtract
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.functions.BiFunction
import timber.log.Timber

internal class UpdateDoctorInsurancesUseCaseImpl(
    private val progressRepository: ProgressRepository,
    private val practitionerInsuranceRepository: PractitionerInsuranceRepository
) : UpdateDoctorInsurancesUseCase {
    override fun invoke(ids: Set<Int>): Observable<RequestResultModel<Set<Int>>> =
        getInsurances()
            .flatMapResult({ serverIds ->
                val addDelta = ids.subtract(serverIds)
                val removeDelta = serverIds.subtract(ids)
                Single.zip(
                    addInsurances(addDelta),
                    removeInsurances(removeDelta),
                    BiFunction { t1, t2 ->
                        var addResult = false
                        var removeResult = false
                        t1.processResult({ addResult = it }, { it })
                        t2.processResult({ removeResult = it }, { it })
                        return@BiFunction (addResult || removeResult).asResult()
                    })
                    .flatMapObservable { result ->
                        result.processResult({ updated ->
                            if (!updated) return@flatMapObservable Observable.just(serverIds.asResultModel())
                            getInsurances().mapResult(
                                { it.asResultModel() },
                                { it.toModelError() })
                        }, { Observable.just(it.toModelError()) })

                    }

            }, { Observable.just(it.toModelError()) })

    private fun getInsurances() = practitionerInsuranceRepository.getPractitionerInsurances()
        .trackProgress(progressRepository)

    private fun addInsurances(ids: Set<Int>): Single<RequestResult<Boolean>> =
        Single.just(ids).flatMap {
            if (ids.isEmpty()) return@flatMap Single.just(false.asResult())
            practitionerInsuranceRepository.addInsurances(ids)
                .trackProgress(progressRepository)
                .mapResult({ true.asResult() }, { it })
        }

    private fun removeInsurances(ids: Set<Int>): Single<RequestResult<Boolean>> =
        Single.just(ids).flatMap {
            if (ids.isEmpty()) return@flatMap Single.just(false.asResult())
            practitionerInsuranceRepository.deleteInsurances(ids)
                .trackProgress(progressRepository)
                .mapResult({ true.asResult() }, { it })
        }
}
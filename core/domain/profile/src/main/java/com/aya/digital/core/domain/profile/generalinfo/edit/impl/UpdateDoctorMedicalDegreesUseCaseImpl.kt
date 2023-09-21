package com.aya.digital.core.domain.profile.generalinfo.edit.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.PractitionerRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.generalinfo.edit.UpdateDoctorMedicalDegreesUseCase
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.flatMapResult
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction

internal class UpdateDoctorMedicalDegreesUseCaseImpl(
    private val practitionerRepository: PractitionerRepository,
    private val progressRepository: ProgressRepository
) : UpdateDoctorMedicalDegreesUseCase {
    override fun invoke(ids: Set<Int>): Observable<RequestResultModel<Boolean>> =
        getMedicalDegrees()
            .flatMapResult({ serverIds ->
                val addDelta = ids.subtract(serverIds.map { it.id }.toSet())
                val removeDelta = serverIds.map { it.id }.subtract(ids)
                Single.zip(
                    addMedicalDegrees(addDelta),
                    removeMedicalDegrees(removeDelta),
                    BiFunction { t1, t2 ->
                        var addResult = false
                        var removeResult = false
                        t1.processResult({ addResult = it }, { it })
                        t2.processResult({ removeResult = it }, { it })
                        return@BiFunction (addResult || removeResult).asResult()
                    })
                    .flatMapObservable { result ->
                        result.processResult({ updated ->
                            if (!updated) return@flatMapObservable Observable.just(false.asResultModel())
                            getMedicalDegrees().mapResult(
                                { true.asResultModel() },
                                { it.toModelError() })
                        }, { Observable.just(it.toModelError()) })

                    }

            }, { Observable.just(it.toModelError()) })


    private fun getMedicalDegrees() = practitionerRepository.getDoctorMedicalDegrees()
        .trackProgress(progressRepository)

    private fun addMedicalDegrees(ids: Set<Int>): Single<RequestResult<Boolean>> =
        Single.just(ids).flatMap {
            if (ids.isEmpty()) return@flatMap Single.just(false.asResult())
            practitionerRepository.addDoctorMedicalDegrees(ids)
                .trackProgress(progressRepository)
                .mapResult({ true.asResult() }, { it })
        }

    private fun removeMedicalDegrees(ids: Set<Int>): Single<RequestResult<Boolean>> =
        Single.just(ids).flatMap {
            if (ids.isEmpty()) return@flatMap Single.just(false.asResult())
            practitionerRepository.removeDoctorMedicalDegrees(ids)
                .trackProgress(progressRepository)
                .mapResult({ true.asResult() }, { it })
        }
}
package com.aya.digital.core.domain.profile.generalinfo.edit.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.PractitionerRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.generalinfo.edit.UpdateDoctorLanguagesUseCase
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.flatMapResult
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction

internal class UpdateDoctorLanguagesUseCaseImpl(
    private val practitionerRepository: PractitionerRepository,
    private val progressRepository: ProgressRepository
) : UpdateDoctorLanguagesUseCase {
    override fun invoke(ids: Set<Int>): Observable<RequestResultModel<Set<Int>>> =
        getLanguages()
            .flatMapResult({ serverIds ->
                val addDelta = ids.subtract(serverIds.map { it.id }.toSet())
                val removeDelta = serverIds.map { it.id }.subtract(ids)
                Single.zip(
                    addLanguages(addDelta),
                    removeLanguages(removeDelta),
                    BiFunction { t1, t2 ->
                        var addResult = false
                        var removeResult = false
                        t1.processResult({ addResult = it }, { it })
                        t2.processResult({ removeResult = it }, { it })
                        return@BiFunction (addResult || removeResult).asResult()
                    })
                    .flatMapObservable { result ->
                        result.processResult({ updated ->
                            if (!updated) return@flatMapObservable Observable.just(serverIds.map { it.id }
                                .toSet().asResultModel())
                            getLanguages().mapResult(
                                { it.map { it.id }.toSet().asResultModel() },
                                { it.toModelError() })
                        }, { Observable.just(it.toModelError()) })

                    }

            }, { Observable.just(it.toModelError()) })


    private fun getLanguages() = practitionerRepository.getDoctorLanguages()
        .trackProgress(progressRepository)

    private fun addLanguages(ids: Set<Int>): Single<RequestResult<Boolean>> =
        Single.just(ids).flatMap {
            if (ids.isEmpty()) return@flatMap Single.just(false.asResult())
            practitionerRepository.addDoctorLanguages(ids)
                .trackProgress(progressRepository)
                .mapResult({ true.asResult() }, { it })
        }

    private fun removeLanguages(ids: Set<Int>): Single<RequestResult<Boolean>> =
        Single.just(ids).flatMap {
            if (ids.isEmpty()) return@flatMap Single.just(false.asResult())
            practitionerRepository.removeDoctorLanguages(ids)
                .trackProgress(progressRepository)
                .mapResult({ true.asResult() }, { it })
        }
}
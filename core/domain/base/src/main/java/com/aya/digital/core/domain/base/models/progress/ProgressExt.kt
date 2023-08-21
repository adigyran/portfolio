package com.aya.digital.core.domain.base.models.progress

import com.aya.digital.core.data.progress.repository.ProgressRepository
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

fun <T : Any> Single<T>.trackProgress(progressRepository: ProgressRepository
): Single<T> =
this.doOnSubscribe { progressRepository.setProgress(true) }
    .doOnSuccess { progressRepository.setProgress(false) }

fun <T : Any> Observable<T>.trackProgress(progressRepository: ProgressRepository
): Observable<T> =
    this.doOnSubscribe { progressRepository.setProgress(true) }
        .doOnComplete{ progressRepository.setProgress(false) }


fun <T : Any> Maybe<T>.trackProgress(progressRepository: ProgressRepository
): Maybe<T> =
    this.doOnSubscribe { progressRepository.setProgress(true) }
        .doOnComplete{ progressRepository.setProgress(false) }
fun <T : Any> Flowable<T>.trackProgress(progressRepository: ProgressRepository
): Flowable<T> =
    this.doOnSubscribe { progressRepository.setProgress(true) }
        .doOnComplete{ progressRepository.setProgress(false) }
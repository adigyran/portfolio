package com.aya.digital.core.repository.progress

import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.datasource.ScheduleDataSource
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.ext.retrofitResponseToResult
import com.aya.digital.core.ext.retryOnError
import com.aya.digital.core.networkbase.CommonUtils
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

internal class ProgressRepositoryImpl(private val progressSubject: PublishSubject<Boolean> = PublishSubject.create()) :
    ProgressRepository {


    override fun setProgress(inProgress: Boolean) = progressSubject.onNext(inProgress)


    override fun listenForProgress(): Observable<Boolean> = progressSubject

}
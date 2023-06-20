package com.aya.digital.core.data.progress.repository

import io.reactivex.rxjava3.core.Observable

interface ProgressRepository {
    fun setProgress(inProgress:Boolean)
    fun listenForProgress(): Observable<Boolean>

}
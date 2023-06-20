package com.aya.digital.core.domain.root.progress

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ListenForProgressUseCase {
    operator fun invoke(): Observable<Boolean>
}


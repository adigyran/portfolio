package com.aya.digital.core.domain.home.lastupdates

import com.aya.digital.core.domain.home.lastupdates.model.LastUpdatesModel
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Observable

interface GetLastUpdatesUseCase {
    operator fun invoke(): Observable<RequestResult<HashMap<String, LastUpdatesModel>>>
}
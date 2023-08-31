package com.aya.digital.core.domain.home.lastupdates

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.home.lastupdates.model.LastUpdatesModel
import io.reactivex.rxjava3.core.Single

interface GetLastUpdatesUseCase {
    operator fun invoke(): Single<RequestResultModel<HashMap<String, LastUpdatesModel>>>
}
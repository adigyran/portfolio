package com.aya.digital.core.domain.appointment.telehealth

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlin.time.Duration

interface GetTeleHealthTimeWindowUseCase {
    operator fun invoke(): Single<RequestResultModel<Duration>>

}
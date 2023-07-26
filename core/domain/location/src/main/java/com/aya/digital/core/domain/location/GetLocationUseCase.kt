package com.aya.digital.core.domain.location


import com.aya.digital.core.domain.location.model.ResultModel
import io.reactivex.rxjava3.core.Observable

interface GetLocationUseCase {
    operator fun invoke(): Observable<ResultModel>
}
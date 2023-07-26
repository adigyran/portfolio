package com.aya.digital.core.domain.location


import com.aya.digital.core.domain.location.model.Result
import io.reactivex.rxjava3.core.Observable

interface GetLocationUseCase {
    operator fun invoke(id:Int): Observable<Result>
}
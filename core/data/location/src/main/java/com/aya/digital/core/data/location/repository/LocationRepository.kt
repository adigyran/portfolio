package com.aya.digital.core.data.location.repository

import com.aya.digital.core.data.location.Result
import io.reactivex.rxjava3.core.Observable

interface LocationRepository {
    fun getLocation():Observable<Result>

    fun test()
}
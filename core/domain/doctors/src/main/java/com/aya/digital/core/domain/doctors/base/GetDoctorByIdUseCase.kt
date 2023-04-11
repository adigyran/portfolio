package com.aya.digital.core.domain.doctors.base

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.doctors.base.model.DoctorModel
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface GetDoctorByIdUseCase {
    operator fun invoke(id:Int): Single<RequestResultModel<DoctorModel>>

}
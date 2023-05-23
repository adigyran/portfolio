package com.aya.digital.core.domain.doctors.base

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import io.reactivex.rxjava3.core.Single

interface GetDoctorByIdUseCase {
    operator fun invoke(id:Int): Single<RequestResultModel<DoctorModel>>

}
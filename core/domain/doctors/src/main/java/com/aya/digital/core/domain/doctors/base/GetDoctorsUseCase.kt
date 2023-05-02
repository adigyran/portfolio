package com.aya.digital.core.domain.doctors.base

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.doctors.base.model.DoctorModel
import io.reactivex.rxjava3.core.Flowable

interface GetDoctorsUseCase {
    operator fun invoke(): Flowable<RequestResultModel<List<DoctorModel>>>

}
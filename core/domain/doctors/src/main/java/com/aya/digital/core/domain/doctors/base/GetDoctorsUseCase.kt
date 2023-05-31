package com.aya.digital.core.domain.doctors.base

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.base.models.doctors.DoctorPaginationModel
import io.reactivex.rxjava3.core.Flowable

interface GetDoctorsUseCase {
    operator fun invoke(
        cursor: String?,
        cities: List<String>?,
        specialities: List<Int>?,
        insurances: List<Int>?
    ): Flowable<RequestResultModel<DoctorPaginationModel>>

}
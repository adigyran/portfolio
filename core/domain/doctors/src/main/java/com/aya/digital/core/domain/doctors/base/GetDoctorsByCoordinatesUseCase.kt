package com.aya.digital.core.domain.doctors.base

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.base.models.doctors.DoctorPaginationModel
import io.reactivex.rxjava3.core.Flowable

interface GetDoctorsByCoordinatesUseCase {
    operator fun invoke(
        lat:Double,
        lon:Double,
        cities: List<String>?,
        specialities: List<Int>?,
        insurances: List<Int>?
    ): Flowable<RequestResultModel<DoctorPaginationModel>>

}
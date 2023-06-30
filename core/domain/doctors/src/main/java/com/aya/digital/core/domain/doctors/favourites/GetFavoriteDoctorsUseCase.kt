package com.aya.digital.core.domain.doctors.favourites

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface GetFavoriteDoctorsUseCase {
    operator fun invoke(): Flowable<RequestResultModel<List<Int>>>

}
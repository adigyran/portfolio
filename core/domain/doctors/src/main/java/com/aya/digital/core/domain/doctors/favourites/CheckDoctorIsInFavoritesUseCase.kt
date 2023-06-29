package com.aya.digital.core.domain.doctors.favourites

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import io.reactivex.rxjava3.core.Single

interface CheckDoctorIsInFavoritesUseCase {
    operator fun invoke(id:Int): Single<RequestResultModel<Boolean>>

}
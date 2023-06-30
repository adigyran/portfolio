package com.aya.digital.core.domain.doctors.favourites.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.doctors.repository.DoctorRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.doctors.favourites.RemoveDoctorFromFavoritesUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class RemoveDoctorFromFavoritesUseCaseImpl(
    private val doctorRepository: DoctorRepository,
    private val progressRepository: ProgressRepository
) : RemoveDoctorFromFavoritesUseCase {
    override fun invoke(id: Int): Single<RequestResultModel<Boolean>>  =
        doctorRepository.removeDoctorFromFavorites(id)
            .trackProgress(progressRepository)
            .mapResult({it.asResultModel()},{it.toModelError()})
}
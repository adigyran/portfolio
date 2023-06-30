package com.aya.digital.core.domain.doctors.favourites.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.doctors.repository.DoctorRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.doctors.favourites.AddDoctorToFavoritesUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class AddDoctorToFavoritesUseCaseImpl(private val doctorRepository: DoctorRepository,private val progressRepository: ProgressRepository) : AddDoctorToFavoritesUseCase {
    override fun invoke(id: Int): Single<RequestResultModel<Boolean>> =
        doctorRepository.addDoctorToFavorites(id)
            .trackProgress(progressRepository)
            .mapResult({it.asResultModel()},{it.toModelError()})
}
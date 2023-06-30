package com.aya.digital.core.domain.doctors.favourites.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.doctors.repository.DoctorRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.doctors.favourites.GetFavoriteDoctorsUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Flowable

internal class GetFavoriteDoctorsUseCaseImpl(
    private val doctorRepository: DoctorRepository,
    private val progressRepository: ProgressRepository
) : GetFavoriteDoctorsUseCase {
    override fun invoke(): Flowable<RequestResultModel<List<Int>>> =
        doctorRepository.getFavoriteDoctors()
            .trackProgress(progressRepository)
            .mapResult({ it.asResultModel() }, { it.toModelError() })
}
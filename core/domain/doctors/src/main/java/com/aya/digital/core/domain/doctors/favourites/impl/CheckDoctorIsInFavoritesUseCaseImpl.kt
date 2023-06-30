package com.aya.digital.core.domain.doctors.favourites.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.doctors.repository.DoctorRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.doctors.favourites.CheckDoctorIsInFavoritesUseCase
import io.reactivex.rxjava3.core.Single

internal class CheckDoctorIsInFavoritesUseCaseImpl(private val doctorRepository: DoctorRepository, private val progressRepository: ProgressRepository) : CheckDoctorIsInFavoritesUseCase {
    override fun invoke(id: Int): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }
}
package com.aya.digital.core.domain.doctors.base.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.doctors.repository.DoctorRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.doctors.DoctorPaginationModel
import com.aya.digital.core.domain.base.models.doctors.mapToDoctorModel
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.doctors.base.GetDoctorsByCoordinatesUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Flowable

internal class GetDoctorsByCoordinatesUseCaseImpl(
    private val doctorRepository: DoctorRepository,
    private val progressRepository: ProgressRepository
) : GetDoctorsByCoordinatesUseCase {
    override fun invoke(
        lat: Double?,
        lon: Double?
    ): Flowable<RequestResultModel<DoctorPaginationModel>> =
        doctorRepository.fetchDoctors(lat = lat, long = lon)
            .trackProgress(progressRepository)
            .mapResult({ paginationModel ->
                DoctorPaginationModel(
                    totalItems = paginationModel.totalResults?:0,
                    cursor = paginationModel.scrollToken,
                    doctors = paginationModel.data.map { doctorData -> doctorData.mapToDoctorModel() }
                ).asResultModel()
                //it.data.map { doctorData -> doctorData.mapToDoctorModel() }.asResultModel()
            }, { it.toModelError() })
}
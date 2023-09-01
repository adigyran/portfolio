package com.aya.digital.core.domain.doctors.base.impl

import com.aya.digital.core.data.base.dataprocessing.PaginationCursorModel
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.doctors.DoctorData
import com.aya.digital.core.data.doctors.repository.DoctorRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.doctors.base.GetDoctorsUseCase
import com.aya.digital.core.domain.base.models.doctors.DoctorPaginationModel
import com.aya.digital.core.domain.base.models.doctors.mapToDoctorModel
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Flowable

internal class GetDoctorsUseCaseImpl(
    private val doctorRepository: DoctorRepository,
    private val progressRepository: ProgressRepository
) :
    GetDoctorsUseCase {
    var paginationPageModel: PaginationCursorModel<DoctorData>? = null
    override fun invoke(
        cursor: String?,
        cities: List<String>?,
        specialities: List<Int>?,
        insurances: List<Int>?
    ): Flowable<RequestResultModel<DoctorPaginationModel>> =
        doctorRepository.fetchDoctors(
            scrollId = cursor,
            specialityCodes = specialities,
            cities = cities,
            insurances = insurances
        )
            .trackProgress(progressRepository)
            .mapResult({ paginationModel ->
                paginationPageModel = paginationModel
                val asResultModel = DoctorPaginationModel(
                    totalItems = paginationModel.totalResults ?: 0,
                    cursor = paginationModel.scrollToken,
                    doctors = paginationModel.data.map { doctorData -> doctorData.mapToDoctorModel() }
                ).asResultModel()
                asResultModel
                //it.data.map { doctorData -> doctorData.mapToDoctorModel() }.asResultModel()
            }, { it.toModelError() })
}
package com.aya.digital.core.domain.doctors.base.impl

import com.aya.digital.core.data.base.dataprocessing.PaginationCursorModel
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.doctors.DoctorData
import com.aya.digital.core.data.doctors.repository.DoctorRepository
import com.aya.digital.core.domain.doctors.base.GetDoctorsUseCase
import com.aya.digital.core.domain.doctors.base.model.DoctorModel
import com.aya.digital.core.domain.doctors.base.model.mapToDoctorModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Flowable

internal class GetDoctorsUseCaseImpl(private val doctorRepository: DoctorRepository) :
    GetDoctorsUseCase {
    var paginationPageModel: PaginationCursorModel<DoctorData>? = null
    override fun invoke(): Flowable<RequestResultModel<List<DoctorModel>>> =
        doctorRepository.fetchDoctors()
            .mapResult({
                paginationPageModel = it
                it.data.map { doctorData -> doctorData.mapToDoctorModel()  }.asResultModel()
            }, { it.toModelError() })

}
package com.aya.digital.core.domain.doctors.base.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.doctors.repository.DoctorRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.doctors.base.GetDoctorByIdUseCase
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.domain.base.models.doctors.mapToDoctorModel
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class GetDoctorByIdUseCaseImpl(
    private val doctorRepository: DoctorRepository,
    private val progressRepository: ProgressRepository
) :
    GetDoctorByIdUseCase {
    override fun invoke(id: Int): Single<RequestResultModel<DoctorModel>> = doctorRepository
        .fetchDoctorById(id)
        .trackProgress(progressRepository)
        .mapResult({ it.mapToDoctorModel().asResultModel() }, { it.toModelError() })


}
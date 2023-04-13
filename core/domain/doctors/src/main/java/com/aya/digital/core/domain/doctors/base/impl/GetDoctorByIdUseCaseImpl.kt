package com.aya.digital.core.domain.doctors.base.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.doctors.DoctorData
import com.aya.digital.core.data.doctors.repository.DoctorRepository
import com.aya.digital.core.domain.doctors.base.GetDoctorByIdUseCase
import com.aya.digital.core.domain.doctors.base.model.DoctorModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class GetDoctorByIdUseCaseImpl(private val doctorRepository: DoctorRepository) :
    GetDoctorByIdUseCase {
    override fun invoke(id: Int): Single<RequestResultModel<DoctorModel>> = doctorRepository
        .fetchDoctorById(id)
        .mapResult({ it.mapToDoctorModel().asResultModel() }, { it.toModelError() })

    private fun DoctorData.mapToDoctorModel() = DoctorModel(
        id = id,
        firstName = firstName,
        lastName = lastName,
        middleName = middleName,
        avatarPhotoLink = avatarPhotoLink,
        bio = bio,
        clinics = clinics.map { DoctorModel.ClinicModel(it.name) },
        location = location?.let {
            DoctorModel.LocationModel(
                it.latitude ?: 0.0,
                it.longitude ?: 0.0
            )
        } ?: DoctorModel.LocationModel(0.0, 0.0),
        postCode = postalCode,
        city = city,
        address = address,
        specialities = this.specialities.map { DoctorModel.SpecialityModel(it.specialtyName) }
    )
}
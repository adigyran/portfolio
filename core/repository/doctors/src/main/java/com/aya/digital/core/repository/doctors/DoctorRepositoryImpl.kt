package com.aya.digital.core.repository.doctors

import com.aya.digital.core.data.doctors.DoctorData
import com.aya.digital.core.data.doctors.mappers.DoctorDataMapper
import com.aya.digital.core.data.doctors.repository.DoctorRepository
import com.aya.digital.core.datasource.PractitionersDataSource
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Single

internal class DoctorRepositoryImpl(
    private val practitionersDataSource: PractitionersDataSource,
    private val doctorDataMapper: DoctorDataMapper
) : DoctorRepository {
    override fun fetchDoctorById(id: Int): Single<RequestResult<DoctorData>> {
        TODO("Not yet implemented")
    }
}
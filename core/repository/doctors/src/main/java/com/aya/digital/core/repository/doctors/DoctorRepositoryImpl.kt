package com.aya.digital.core.repository.doctors

import com.aya.digital.core.data.base.dataprocessing.PaginationCursorModel
import com.aya.digital.core.data.doctors.DoctorData
import com.aya.digital.core.data.doctors.mappers.DoctorDataMapper
import com.aya.digital.core.data.doctors.repository.DoctorRepository
import com.aya.digital.core.datasource.PractitionersDataSource
import com.aya.digital.core.datasource.ScheduleDataSource
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.ext.retrofitResponseToResult
import com.aya.digital.core.ext.retryOnError
import com.aya.digital.core.networkbase.CommonUtils
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

internal class DoctorRepositoryImpl(
    private val practitionersDataSource: PractitionersDataSource,
    private val doctorDataMapper: DoctorDataMapper
) : DoctorRepository {
    override fun fetchDoctorById(id: Int): Single<RequestResult<DoctorData>> =
        practitionersDataSource.fetchPractitionerById(id)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result ->
                doctorDataMapper.mapFrom(result).asResult()
            }, { it })

    override fun fetchDoctors(): Flowable<RequestResult<PaginationCursorModel<DoctorData>>> =
        practitionersDataSource.fetchPractitioners()
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result ->
                PaginationCursorModel(
                    data = doctorDataMapper.mapFromList(result.data),
                    scrollToken = result.scrollToken,
                    sizeResult = result.sizeResult
                ).asResult()
            }, { it })
}
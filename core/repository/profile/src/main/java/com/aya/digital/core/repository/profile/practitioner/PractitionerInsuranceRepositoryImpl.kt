package com.aya.digital.core.repository.profile.practitioner

import android.content.Context
import com.aya.digital.core.data.profile.repository.PractitionerInsuranceRepository
import com.aya.digital.core.data.profile.repository.PractitionerRepository
import com.aya.digital.core.datasource.ProfilePractitionerDataSource
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.ext.retrofitResponseToResult
import com.aya.digital.core.ext.retryOnError
import com.aya.digital.core.networkbase.CommonUtils
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Observable

internal class PractitionerInsuranceRepositoryImpl(
    private val context: Context,
    private val practitionerDataSource: ProfilePractitionerDataSource
) : PractitionerInsuranceRepository {
    override fun getPractitionerInsurances(): Observable<RequestResult<List<Int>>> =
        practitionerDataSource.getDoctorInsurances()
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({
                it.asResult()
            }, { it })
}
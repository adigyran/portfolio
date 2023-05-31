package com.aya.digital.core.datasource.network

import com.aya.digital.core.network.api.services.PractitionersService
import com.aya.digital.core.network.main.di.modules.createApiService
import com.aya.digital.core.network.model.response.base.PagedCursorResponse
import com.aya.digital.core.network.model.response.doctors.DoctorDataResponse
import com.aya.digital.core.network.model.response.doctors.SpecialityResponse
import com.aya.digital.core.network.model.response.patient.PatientDataResponse
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton


fun practitionersNetworkModule() = DI.Module("practitionersNetworkModule") {
    bind<com.aya.digital.core.datasource.PractitionersDataSource>() with singleton {
        val apiService =
            createApiService<PractitionersService>(
                instance()
            )
        return@singleton RetrofitPractitionersNetwork(apiService)
    }
}

class RetrofitPractitionersNetwork(private val network: PractitionersService) :
    com.aya.digital.core.datasource.PractitionersDataSource {

    override fun fetchPractitioners(
        scrollId: String?, specialityCodes: List<Int>?,
        cities: List<String>?,
        insurances: List<Int>?
    ): Flowable<PagedCursorResponse<DoctorDataResponse>> =
        network.fetchPractitioners(scrollId, specialityCodes, insurances, cities?.firstOrNull())

    override fun fetchPractitionerById(id: Int): Single<DoctorDataResponse> =
        network.fetchPractitionerById(id)

    override fun fetchSpecialities(
        page: Int,
        limit: Int,
        sortingFields: List<String>?,
        sortDirection: String?,
        name: String?
    ): Flowable<PagedCursorResponse<SpecialityResponse>> =
        network.fetchSpecialities(page, limit, sortingFields, sortDirection, name)

    override fun fetchSpeciality(id: Int): Single<SpecialityResponse> =
        network.fetchSpeciality(id)

    override fun getPatient(id: Int): Single<PatientDataResponse> = network.getPatient(id)
}
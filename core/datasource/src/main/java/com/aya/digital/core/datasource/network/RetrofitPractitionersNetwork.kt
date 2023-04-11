package com.aya.digital.core.datasource.network

import com.aya.digital.core.network.api.services.PractitionersService
import com.aya.digital.core.network.main.di.modules.createApiService
import com.aya.digital.core.network.model.response.base.PagedResponse
import com.aya.digital.core.network.model.response.doctors.DoctorDataResponse
import com.aya.digital.core.network.model.response.doctors.SpecialityResponse
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
        page: Int,
        limit: Int,
        search: String?,
        specialty: String?,
        specialtyCode: String?,
        sortingFields: List<String>?,
        sortDirection: String?
    ): Flowable<PagedResponse<DoctorDataResponse>> =
        network.fetchPractitioners(
            page,
            limit,
            search,
            specialty,
            specialtyCode,
            sortingFields,
            sortDirection
        )

    override fun fetchPractitionerById(id: Int): Single<DoctorDataResponse> =
        network.fetchPractitionerById(id)

    override fun fetchSpecialities(
        page: Int,
        limit: Int,
        sortingFields: List<String>?,
        sortDirection: String?,
        name: String?
    ): Flowable<PagedResponse<SpecialityResponse>> =
        network.fetchSpecialities(page, limit, sortingFields, sortDirection, name)

    override fun fetchSpeciality(id: Int): Single<SpecialityResponse> =
        network.fetchSpeciality(id)
}
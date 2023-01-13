package com.aya.digital.core.network.di.services

import com.aya.digital.core.network.api.services.PractitionersService
import com.aya.digital.core.network.datasources.PractitionersDataSource
import com.aya.digital.core.network.di.createApiService
import com.aya.digital.core.network.model.response.base.PagedResponse
import com.aya.digital.core.network.model.response.doctors.DoctorData
import com.aya.digital.core.network.model.response.doctors.PractitionersResponse
import com.aya.digital.core.network.model.response.doctors.SpecialitiesSpeciality
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.eagerSingleton
import org.kodein.di.instance


fun practitionersNetworkModule() = DI.Module("practitionersNetworkModule") {
    bind<PractitionersDataSource>() with eagerSingleton {
        val apiService = createApiService<PractitionersService>(instance())
        return@eagerSingleton RetrofitPractitionersNetwork(apiService)
    }
}

class RetrofitPractitionersNetwork(private val network: PractitionersService) : PractitionersDataSource {

    override fun fetchPractitioners(
        page: Int,
        limit: Int,
        search: String?,
        specialty: String?,
        specialtyCode: String?,
        sortingFields: List<String>?,
        sortDirection: String?
    ): Flowable<PagedResponse<DoctorData>> = network.fetchPractitioners(page, limit, search, specialty, specialtyCode, sortingFields, sortDirection)

    override fun fetchPractitionerById(id: Int): Single<DoctorData> = network.fetchPractitionerById(id)

    override fun searchPractitioners(
        search: String,
        page: Int,
        limit: Int
    ): Flowable<PractitionersResponse> = network.searchPractitioners(search, page, limit)

    override fun searchPractitionersWithCode(
        code: String,
        search: String,
        page: Int,
        limit: Int
    ): Flowable<PractitionersResponse> = network.searchPractitionersWithCode(code, search, page, limit)

    override fun fetchSpecialities(
        page: Int,
        limit: Int,
        sortingFields: List<String>?,
        sortDirection: String?,
        name: String?
    ): Flowable<PagedResponse<SpecialitiesSpeciality>> = network.fetchSpecialities(page, limit, sortingFields, sortDirection, name)

    override fun fetchSpeciality(id: Int): Single<SpecialitiesSpeciality> = network.fetchSpeciality(id)
}
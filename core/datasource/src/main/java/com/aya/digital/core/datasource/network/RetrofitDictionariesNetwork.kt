package com.aya.digital.core.datasource.network

import com.aya.digital.core.datasource.DictionariesDataSource
import com.aya.digital.core.network.api.services.DictionariesService
import com.aya.digital.core.network.main.di.modules.createApiService
import com.aya.digital.core.network.model.response.base.PagedCursorResponse
import com.aya.digital.core.network.model.response.doctors.CityResponse
import com.aya.digital.core.network.model.response.doctors.MedicalDegreeResponse
import com.aya.digital.core.network.model.response.doctors.SpecialityResponse
import com.aya.digital.core.network.model.response.emergencycontact.EmergencyContactTypeResponse
import com.aya.digital.core.network.model.response.language.LanguageResponse
import com.aya.digital.core.network.model.response.profile.InsuranceCompanyResponse
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton


fun dictionariesNetworkModule() = DI.Module("dictionariesNetworkModule") {
    bind<DictionariesDataSource>() with singleton {
        val apiService =
            createApiService<DictionariesService>(instance())
        return@singleton RetrofitDictionariesNetwork(apiService)
    }
}

class RetrofitDictionariesNetwork(private val network: DictionariesService) :
    DictionariesDataSource {
    override fun getInsuranceCompanies(
        searchTerm: String?,
        selectedIds: List<Int>,
        cursor: String?
    ): Flowable<PagedCursorResponse<InsuranceCompanyResponse>> =
        network.getInsurances(searchTerm, cursor, selectedIds)

    override fun getInsuranceCompanyById(id: Int): Single<PagedCursorResponse<InsuranceCompanyResponse>> =
        network.getInsuranceById(id)

    override fun getInsuranceCompaniesByIds(ids: List<Int>): Observable<PagedCursorResponse<InsuranceCompanyResponse>> =
        network.getInsurancesByIds(ids)

    override fun getSpecialisations(
        searchTerm: String?,
        selectedIds: List<Int>,
        cursor: String?
    ): Flowable<PagedCursorResponse<SpecialityResponse>> =
        network.getSpecialities(searchTerm, cursor, selectedIds)

    override fun getCities(
        searchTerm: String?,
        selectedIds: List<Int>,
        cursor: String?
    ): Flowable<PagedCursorResponse<CityResponse.CityContent>> =
        network.getCities(searchTerm, cursor, selectedIds)

    override fun getLanguages(
        searchTerm: String?,
        selectedIds: List<Int>,
        cursor: String?
    ): Flowable<PagedCursorResponse<LanguageResponse>> =
        network.getLanguages(searchTerm, cursor, selectedIds)

    override fun getMedicalDegrees(
        searchTerm: String?,
        selectedIds: List<Int>,
        cursor: String?
    ): Flowable<PagedCursorResponse<MedicalDegreeResponse>> =
        network.getMedicalDegrees(searchTerm, cursor, selectedIds)

    override fun getEmergencyContactsTypes(
        searchTerm: String?,
        selectedIds: List<Int>,
        cursor: String?
    ): Flowable<PagedCursorResponse<EmergencyContactTypeResponse>> = network.getEmergencyContactsTypes(searchTerm,cursor,selectedIds)

    override fun getEmergencyContactsTypeById(id: Int): Single<EmergencyContactTypeResponse>  = network.getEmergencyContactsTypeById(id)
}

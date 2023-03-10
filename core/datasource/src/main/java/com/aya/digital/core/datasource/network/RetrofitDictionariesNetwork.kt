package com.aya.digital.core.datasource.network

import com.aya.digital.core.datasource.DictionariesDataSource
import com.aya.digital.core.network.api.services.DictionariesService
import com.aya.digital.core.network.main.di.modules.createApiService
import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.base.PagedResponse
import com.aya.digital.core.network.model.response.profile.InsuranceCompanyResponse
import com.aya.digital.core.network.model.response.profile.InsurancePolicyResponse
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
    override fun getInsuranceCompanies(searchTerm: String): Flowable<PagedResponse<InsuranceCompanyResponse>>  = network.getInsurances(searchTerm)
    override fun getInsuranceCompanyById(id: Int): Single<List<InsuranceCompanyResponse>>  = network.getInsuranceById(id)
    override fun getInsuranceCompaniesByIds(ids: List<Int>): Observable<List<InsuranceCompanyResponse>> = network.getInsurancesByIds(ids)
}

package com.aya.digital.core.domain.dictionaries.insurancecompany.impl

import com.aya.digital.core.data.base.dataprocessing.PaginationPageModel
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.dictionaries.InsuranceCompanyModel
import com.aya.digital.core.data.dictionaries.repository.DictionariesRepository
import com.aya.digital.core.domain.dictionaries.insurancecompany.GetInsuranceCompanyItemsUseCase
import com.aya.digital.core.domain.dictionaries.base.model.MultiSelectItem
import com.aya.digital.core.domain.dictionaries.insurancecompany.model.InsuranceCompanyItem
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Flowable

internal class GetInsuranceCompanyItemsUseCaseImpl(private val dictionariesRepository: DictionariesRepository) :
    GetInsuranceCompanyItemsUseCase {

    var paginationPageModel: PaginationPageModel<InsuranceCompanyModel>? = null

    override fun invoke(searchTerm: String?): Flowable<RequestResultModel<List<InsuranceCompanyItem>>> =
        dictionariesRepository.getInsuranceCompanies(searchTerm ?: "")
            .mapResult({
                paginationPageModel = it
                it.results.map {
                    InsuranceCompanyItem(it.id, it.name ?: "")
                }.asResultModel()
            }, { it.toModelError() })


}
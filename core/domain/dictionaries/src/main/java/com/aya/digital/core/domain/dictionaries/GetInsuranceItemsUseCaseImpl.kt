package com.aya.digital.core.domain.dictionaries

import com.aya.digital.core.data.base.dataprocessing.PaginationPageModel
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.dictionaries.InsuranceCompanyModel
import com.aya.digital.core.data.dictionaries.repository.DictionariesRepository
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Flowable

internal class GetInsuranceItemsUseCaseImpl(private val dictionariesRepository: DictionariesRepository) :
    GetInsuranceItemsUseCase {

    var paginationPageModel: PaginationPageModel<InsuranceCompanyModel>? = null

    override fun invoke(searchTerm: String?): Flowable<RequestResultModel<List<MultiSelectItem>>> =
        dictionariesRepository.getInsuranceCompanies(searchTerm ?: "")
            .mapResult({
                paginationPageModel = it
                it.results.map {
                    MultiSelectItem(it.id, it.name ?: "")
                }.asResultModel()
            }, { it.toModelError() })


}
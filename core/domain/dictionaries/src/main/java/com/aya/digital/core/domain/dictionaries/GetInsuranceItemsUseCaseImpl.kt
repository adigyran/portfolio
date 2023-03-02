package com.aya.digital.core.domain.dictionaries

import com.aya.digital.core.data.base.dataprocessing.PaginationPageModel
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.InsuranceModel
import com.aya.digital.core.data.profile.repository.DictionariesRepository
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.network.model.request.InsuranceBody
import io.reactivex.rxjava3.core.Flowable

internal class GetInsuranceItemsUseCaseImpl(private val dictionariesRepository: DictionariesRepository) :
    GetInsuranceItemsUseCase {

    var paginationPageModel: PaginationPageModel<InsuranceModel>? = null

    override fun invoke(searchTerm: String?): Flowable<RequestResultModel<List<MultiSelectItem>>> =
        dictionariesRepository.getInsurances(searchTerm ?: "")
            .mapResult({
                paginationPageModel = it
                it.results.map {
                    MultiSelectItem(it.id, it.name ?: "")
                }.asResultModel()
            }, { it.toModelError() })


}
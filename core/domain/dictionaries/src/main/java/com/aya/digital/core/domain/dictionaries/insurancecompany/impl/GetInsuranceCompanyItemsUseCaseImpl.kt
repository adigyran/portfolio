package com.aya.digital.core.domain.dictionaries.insurancecompany.impl

import com.aya.digital.core.data.base.dataprocessing.PaginationCursorModel
import com.aya.digital.core.data.base.dataprocessing.PaginationPageModel
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.dictionaries.InsuranceCompanyModel
import com.aya.digital.core.data.dictionaries.repository.DictionariesRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.dictionaries.insurancecompany.GetInsuranceCompanyItemsUseCase
import com.aya.digital.core.domain.dictionaries.base.model.MultiSelectItem
import com.aya.digital.core.domain.dictionaries.insurancecompany.model.InsuranceCompanyItem
import com.aya.digital.core.domain.dictionaries.insurancecompany.model.InsuranceCompanyItemPaginationModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Flowable

internal class GetInsuranceCompanyItemsUseCaseImpl(
    private val dictionariesRepository: DictionariesRepository,
    private val progressRepository: ProgressRepository
) :
    GetInsuranceCompanyItemsUseCase {

    var paginationPageModel: PaginationCursorModel<InsuranceCompanyModel>? = null

    override fun invoke(searchTerm: String?,selectedItems:Set<Int>,cursor:String?): Flowable<RequestResultModel<InsuranceCompanyItemPaginationModel>> =
        dictionariesRepository.getInsuranceCompanies(searchTerm,selectedItems.toList(),cursor)
            .trackProgress(progressRepository)
            .mapResult({ paginationModel ->
                paginationPageModel = paginationModel
                InsuranceCompanyItemPaginationModel(
                    cursor = paginationModel.scrollToken,
                    items = paginationModel.data.map {InsuranceCompanyItem(it.id, it.name ?: "") }
                ).asResultModel()
            }, { it.toModelError() })


}
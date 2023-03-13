package com.aya.digital.core.domain.dictionaries.base.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.mapResult
import com.aya.digital.core.domain.dictionaries.insurancecompany.GetInsuranceCompanyItemsUseCase
import com.aya.digital.core.domain.dictionaries.base.GetMultiSelectItemsUseCase
import com.aya.digital.core.domain.dictionaries.base.model.MultiSelectItem
import com.aya.digital.core.util.requestcodes.RequestCodes
import io.reactivex.rxjava3.core.Flowable

internal class GetMultiSelectItemsUseCaseImpl(private val getInsuranceCompanyItemsUseCase: GetInsuranceCompanyItemsUseCase) :
    GetMultiSelectItemsUseCase {
    override fun invoke(
        searchTerm: String?,
        type: String
    ): Flowable<RequestResultModel<List<MultiSelectItem>>> = when (type) {
        RequestCodes.INSURANCE_LIST_REQUEST_CODE -> getInsuranceCompanyItemsUseCase(searchTerm).mapResult({items-> items.map { MultiSelectItem(it.id,it.text) }.asResultModel()},{it})
        else -> {
            Flowable.empty()
        }
    }
}
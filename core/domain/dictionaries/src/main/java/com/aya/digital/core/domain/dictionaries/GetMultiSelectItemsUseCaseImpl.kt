package com.aya.digital.core.domain.dictionaries

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.util.requestcodes.RequestCodes
import io.reactivex.rxjava3.core.Flowable

internal class GetMultiSelectItemsUseCaseImpl(private val getInsuranceItemsUseCase: GetInsuranceItemsUseCase) :
    GetMultiSelectItemsUseCase {
    override fun invoke(
        searchTerm: String?,
        type: String
    ): Flowable<RequestResultModel<List<MultiSelectItem>>> = when (type) {
        RequestCodes.INSURANCE_LIST_REQUEST_CODE -> getInsuranceItemsUseCase(searchTerm)
        else -> {
            Flowable.empty()
        }
    }
}
package com.aya.digital.core.domain.dictionaries

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.dictionaries.MultiSelectItem
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface GetInsuranceItemsUseCase {
    operator fun invoke(searchTerm:String?): Flowable<RequestResultModel<List<MultiSelectItem>>>
}
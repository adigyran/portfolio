package com.aya.digital.core.domain.dictionaries.base

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.dictionaries.base.model.MultiSelectItem
import io.reactivex.rxjava3.core.Flowable

interface GetMultiSelectItemsUseCase {
    operator fun invoke(searchTerm:String?, type: String): Flowable<RequestResultModel<List<MultiSelectItem>>>
}


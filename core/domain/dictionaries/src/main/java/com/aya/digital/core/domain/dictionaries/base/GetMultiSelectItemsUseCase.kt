package com.aya.digital.core.domain.dictionaries.base

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.dictionaries.base.model.MultiSelectItem
import com.aya.digital.core.domain.dictionaries.base.model.MultiSelectItemPaginationModel
import io.reactivex.rxjava3.core.Flowable

interface GetMultiSelectItemsUseCase {
    operator fun invoke(searchTerm:String?,selectedItems:Set<Int>,cursor:String?, type: String): Flowable<RequestResultModel<MultiSelectItemPaginationModel>>
}


package com.aya.digital.core.domain.dictionaries.languages

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.dictionaries.languages.model.LanguageItemPaginationModel
import io.reactivex.rxjava3.core.Flowable

fun interface GetLanguageItemsUseCase {
    operator fun invoke(
        searchTerm: String?,
        selectedItems:Set<Int>,
        cursor: String?
    ): Flowable<RequestResultModel<LanguageItemPaginationModel>>
}
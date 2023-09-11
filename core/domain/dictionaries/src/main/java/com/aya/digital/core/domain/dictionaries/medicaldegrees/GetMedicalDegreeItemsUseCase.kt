package com.aya.digital.core.domain.dictionaries.medicaldegrees

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.dictionaries.languages.model.LanguageItemPaginationModel
import com.aya.digital.core.domain.dictionaries.medicaldegrees.model.MedicalDegreeItemPaginationModel
import io.reactivex.rxjava3.core.Flowable

fun interface GetMedicalDegreeItemsUseCase {
    operator fun invoke(
        searchTerm: String?,
        selectedItems:Set<Int>,
        cursor: String?
    ): Flowable<RequestResultModel<MedicalDegreeItemPaginationModel>>
}
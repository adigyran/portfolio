package com.aya.digital.core.domain.dictionaries.cities.impl

import com.aya.digital.core.data.base.dataprocessing.PaginationCursorModel
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.dictionaries.CityModel
import com.aya.digital.core.data.dictionaries.LanguageModel
import com.aya.digital.core.data.dictionaries.repository.DictionariesRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.dictionaries.languages.GetLanguageItemsUseCase
import com.aya.digital.core.domain.dictionaries.languages.model.LanguageItem
import com.aya.digital.core.domain.dictionaries.languages.model.LanguageItemPaginationModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Flowable

internal class GetLanguageItemsUseCaseImpl(
    private val dictionariesRepository: DictionariesRepository,
    private val progressRepository: ProgressRepository
) : GetLanguageItemsUseCase {

    var paginationPageModel: PaginationCursorModel<LanguageModel>? = null

    override fun invoke(
        searchTerm: String?,
        selectedItems:Set<Int>,
        cursor: String?
    ): Flowable<RequestResultModel<LanguageItemPaginationModel>> =
        dictionariesRepository.getLanguages(searchTerm,selectedItems.toList(),cursor)
            .trackProgress(progressRepository)
            .mapResult({ paginationModel ->
                paginationPageModel = paginationModel
                LanguageItemPaginationModel(
                    cursor = paginationModel.scrollToken,
                    items = paginationModel.data.map { LanguageItem(it.id, it.name ?: "") }
                ).asResultModel()
            }, { it.toModelError() })

}
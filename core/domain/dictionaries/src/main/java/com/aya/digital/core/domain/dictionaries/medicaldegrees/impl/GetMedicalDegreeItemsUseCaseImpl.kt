package com.aya.digital.core.domain.dictionaries.medicaldegrees.impl

import com.aya.digital.core.data.base.dataprocessing.PaginationCursorModel
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.dictionaries.LanguageModel
import com.aya.digital.core.data.dictionaries.MedicalDegreeModel
import com.aya.digital.core.data.dictionaries.repository.DictionariesRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.dictionaries.languages.GetLanguageItemsUseCase
import com.aya.digital.core.domain.dictionaries.languages.model.LanguageItem
import com.aya.digital.core.domain.dictionaries.languages.model.LanguageItemPaginationModel
import com.aya.digital.core.domain.dictionaries.medicaldegrees.GetMedicalDegreeItemsUseCase
import com.aya.digital.core.domain.dictionaries.medicaldegrees.model.MedicalDegreeItem
import com.aya.digital.core.domain.dictionaries.medicaldegrees.model.MedicalDegreeItemPaginationModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Flowable

internal class GetMedicalDegreeItemsUseCaseImpl(
    private val dictionariesRepository: DictionariesRepository,
    private val progressRepository: ProgressRepository
) : GetMedicalDegreeItemsUseCase {

    var paginationPageModel: PaginationCursorModel<MedicalDegreeModel>? = null

    override fun invoke(
        searchTerm: String?,
        selectedItems:Set<Int>,
        cursor: String?
    ): Flowable<RequestResultModel<MedicalDegreeItemPaginationModel>> =
        dictionariesRepository.getMedicalDegrees(searchTerm,selectedItems.toList(),cursor)
            .trackProgress(progressRepository)
            .mapResult({ paginationModel ->
                paginationPageModel = paginationModel
                MedicalDegreeItemPaginationModel(
                    cursor = paginationModel.scrollToken,
                    items = paginationModel.data.map { MedicalDegreeItem(it.id, it.name ?: "") }
                ).asResultModel()
            }, { it.toModelError() })

}
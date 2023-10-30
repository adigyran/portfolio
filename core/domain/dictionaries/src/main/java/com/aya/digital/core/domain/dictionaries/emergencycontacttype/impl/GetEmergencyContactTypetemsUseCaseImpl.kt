package com.aya.digital.core.domain.dictionaries.emergencycontacttype.impl

import com.aya.digital.core.data.base.dataprocessing.PaginationCursorModel
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.dictionaries.EmergencyContactTypeModel
import com.aya.digital.core.data.dictionaries.repository.DictionariesRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.dictionaries.emergencycontacttype.GetEmergencyContactTypeItemsUseCase
import com.aya.digital.core.domain.dictionaries.emergencycontacttype.model.EmergencyContactTypeItem
import com.aya.digital.core.domain.dictionaries.emergencycontacttype.model.EmergencyContactTypeItemPaginationModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Flowable

internal class GetEmergencyContactTypetemsUseCaseImpl(
    private val dictionariesRepository: DictionariesRepository,
    private val progressRepository: ProgressRepository
) : GetEmergencyContactTypeItemsUseCase {

    var paginationPageModel: PaginationCursorModel<EmergencyContactTypeModel>? = null

    override fun invoke(
        searchTerm: String?,
        selectedItems:Set<Int>,
        cursor: String?
    ): Flowable<RequestResultModel<EmergencyContactTypeItemPaginationModel>> =
        dictionariesRepository.getEmergencyContactTypes(searchTerm,selectedItems.toList(),cursor)
            .trackProgress(progressRepository)
            .mapResult({ paginationModel ->
                paginationPageModel = paginationModel
                EmergencyContactTypeItemPaginationModel(
                    cursor = paginationModel.scrollToken,
                    items = paginationModel.data.map { EmergencyContactTypeItem(it.id, it.name) }
                ).asResultModel()
            }, { it.toModelError() })

}
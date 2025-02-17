package com.aya.digital.core.domain.dictionaries.speciality.impl

import com.aya.digital.core.data.base.dataprocessing.PaginationCursorModel
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.dictionaries.InsuranceCompanyModel
import com.aya.digital.core.data.dictionaries.SpecialityModel
import com.aya.digital.core.data.dictionaries.repository.DictionariesRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.dictionaries.insurancecompany.model.InsuranceCompanyItem
import com.aya.digital.core.domain.dictionaries.insurancecompany.model.InsuranceCompanyItemPaginationModel
import com.aya.digital.core.domain.dictionaries.speciality.GetSpecialityItemsUseCase
import com.aya.digital.core.domain.dictionaries.speciality.model.SpecialityItem
import com.aya.digital.core.domain.dictionaries.speciality.model.SpecialityItemPaginationModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Flowable

internal class GetSpecialityItemsUseCaseImpl(
    private val dictionariesRepository: DictionariesRepository,
    private val progressRepository: ProgressRepository
) :
    GetSpecialityItemsUseCase {
    var paginationPageModel: PaginationCursorModel<SpecialityModel>? = null

    override fun invoke(
        searchTerm: String?,
        selectedItems: Set<Int>,
        cursor: String?
    ): Flowable<RequestResultModel<SpecialityItemPaginationModel>> =
        dictionariesRepository.getSpecialities(searchTerm, selectedItems.toList(),cursor)
            .trackProgress(progressRepository)
            .mapResult({ paginationModel ->
                paginationPageModel = paginationModel
                SpecialityItemPaginationModel(
                    cursor = paginationModel.scrollToken,
                    items = paginationModel.data.map {
                        SpecialityItem(
                            it.id,
                            it.name ?: "",
                            it.code ?: ""
                        )
                    }
                ).asResultModel()
            }, { it.toModelError() })
}
package com.aya.digital.core.domain.dictionaries.base.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.mapResult
import com.aya.digital.core.domain.dictionaries.insurancecompany.GetInsuranceCompanyItemsUseCase
import com.aya.digital.core.domain.dictionaries.base.GetMultiSelectItemsUseCase
import com.aya.digital.core.domain.dictionaries.base.model.ItemPaginationModelWithCursor
import com.aya.digital.core.domain.dictionaries.base.model.MultiSelectItem
import com.aya.digital.core.domain.dictionaries.base.model.MultiSelectItemPaginationModel
import com.aya.digital.core.domain.dictionaries.cities.GetCityItemsUseCase
import com.aya.digital.core.domain.dictionaries.languages.GetLanguageItemsUseCase
import com.aya.digital.core.domain.dictionaries.speciality.GetSpecialityItemsUseCase
import com.aya.digital.core.util.requestcodes.RequestCodes
import io.reactivex.rxjava3.core.Flowable

internal class GetMultiSelectItemsUseCaseImpl(
    private val getInsuranceCompanyItemsUseCase: GetInsuranceCompanyItemsUseCase,
    private val getSpecialityItemsUseCase: GetSpecialityItemsUseCase,
    private val getCityItemsUseCase: GetCityItemsUseCase,
    private val getLanguageItemsUseCase: GetLanguageItemsUseCase
) :
    GetMultiSelectItemsUseCase {
    override fun invoke(
        searchTerm: String?,
        selectedItems: Set<Int>,
        cursor: String?,
        type: String
    ): Flowable<RequestResultModel<MultiSelectItemPaginationModel>> = when (type) {
        RequestCodes.INSURANCE_LIST_REQUEST_CODE -> getInsuranceCompanyItemsUseCase(
            searchTerm,
            selectedItems,
            cursor
        ).mapResult(
            { paginationModel ->
                paginationModel.mapToMultiselectItem().asResultModel()
            },
            { it })

        RequestCodes.SPECIALITIES_LIST_REQUEST_CODE -> getSpecialityItemsUseCase(
            searchTerm,
            selectedItems,
            cursor
        ).mapResult(
            { paginationModel -> paginationModel.mapToMultiselectItem().asResultModel() },
            { it })

        RequestCodes.LOCATIONS_LIST_REQUEST_CODE -> getCityItemsUseCase(
            searchTerm,
            selectedItems,
            cursor
        ).mapResult(
            { paginationModel -> paginationModel.mapToMultiselectItem().asResultModel() },
            { it })

        RequestCodes.LANGUAGES_LIST_REQUEST_CODE -> getLanguageItemsUseCase(
            searchTerm,
            selectedItems,
            cursor
        ).mapResult(
            { paginationModel -> paginationModel.mapToMultiselectItem().asResultModel() },
            { it })

        else -> {
            Flowable.empty()
        }
    }

    private fun ItemPaginationModelWithCursor.mapToMultiselectItem() =
        MultiSelectItemPaginationModel(
            cursor = this.getCursor(),
            items = this.getItems().map { MultiSelectItem(id = it.id, text = it.name) })

}
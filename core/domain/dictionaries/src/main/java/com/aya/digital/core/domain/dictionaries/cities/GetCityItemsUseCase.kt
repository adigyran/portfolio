package com.aya.digital.core.domain.dictionaries.cities

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.dictionaries.cities.model.CityItem
import com.aya.digital.core.domain.dictionaries.cities.model.CityItemPaginationModel
import io.reactivex.rxjava3.core.Flowable

fun interface GetCityItemsUseCase {
    operator fun invoke(
        searchTerm: String?,
        selectedItems:Set<Int>,
        cursor: String?
    ): Flowable<RequestResultModel<CityItemPaginationModel>>
}
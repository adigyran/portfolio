package com.aya.digital.core.domain.dictionaries.cities.impl

import com.aya.digital.core.data.base.dataprocessing.PaginationCursorModel
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.dictionaries.repository.DictionariesRepository
import com.aya.digital.core.domain.dictionaries.cities.GetCityItemsUseCase
import com.aya.digital.core.domain.dictionaries.cities.model.CityItem
import com.aya.digital.core.domain.dictionaries.insurancecompany.model.InsuranceCompanyItem
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.network.model.response.doctors.CityResponse
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable

class GetCityItemsUseCaseImpl(
    private val dictionariesRepository: DictionariesRepository
) : GetCityItemsUseCase {

    private var paginationPageModel: CityResponse? = null

    override fun invoke(searchTerm: String?) =
        dictionariesRepository.getCities(searchTerm).mapResult(::mapData, ::mapError)

    private fun mapData(result: CityResponse) =
        result.content.map { CityItem(it.id, it.name) }.asResultModel()
            .also { paginationPageModel = result }

    private fun mapError(error: RequestResult.Error) = error.toModelError()
}
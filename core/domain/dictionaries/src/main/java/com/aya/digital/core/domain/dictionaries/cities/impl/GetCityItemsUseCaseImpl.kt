package com.aya.digital.core.domain.dictionaries.cities.impl

import com.aya.digital.core.data.base.dataprocessing.PaginationCursorModel
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.dictionaries.CityModel
import com.aya.digital.core.data.dictionaries.InsuranceCompanyModel
import com.aya.digital.core.data.dictionaries.repository.DictionariesRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.dictionaries.cities.GetCityItemsUseCase
import com.aya.digital.core.domain.dictionaries.cities.model.CityItem
import com.aya.digital.core.domain.dictionaries.cities.model.CityItemPaginationModel
import com.aya.digital.core.domain.dictionaries.insurancecompany.model.InsuranceCompanyItem
import com.aya.digital.core.domain.dictionaries.insurancecompany.model.InsuranceCompanyItemPaginationModel
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.network.model.response.doctors.CityResponse
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable

class GetCityItemsUseCaseImpl(
    private val dictionariesRepository: DictionariesRepository,
    private val progressRepository: ProgressRepository
) : GetCityItemsUseCase {

    var paginationPageModel: PaginationCursorModel<CityModel>? = null

    override fun invoke(searchTerm: String?): Flowable<RequestResultModel<CityItemPaginationModel>> =
        dictionariesRepository.getCities(searchTerm)
            .trackProgress(progressRepository)
            .mapResult({ paginationModel ->
                paginationPageModel = paginationModel
                CityItemPaginationModel(
                    cursor = paginationModel.scrollToken,
                    items = paginationModel.data.map {CityItem(it.id, it.name ?: "") }
                ).asResultModel()
            }, { it.toModelError() })

}
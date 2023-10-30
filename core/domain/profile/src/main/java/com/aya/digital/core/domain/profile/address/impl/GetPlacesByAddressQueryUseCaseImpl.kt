package com.aya.digital.core.domain.profile.address.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.AddressRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.address.GetPlacesByAddressQueryUseCase
import com.aya.digital.core.domain.profile.address.model.PlacePredictionModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single
import timber.log.Timber

internal class GetPlacesByAddressQueryUseCaseImpl(private val addressRepository: AddressRepository, private val progressRepository: ProgressRepository) : GetPlacesByAddressQueryUseCase {
    init {
        addressRepository.initAutocompleteSession()
    }
    override fun invoke(address: String): Single<RequestResultModel<List<PlacePredictionModel>>> = addressRepository
        .getPlacesPredictionsAddresses(address)
        .trackProgress(progressRepository)
        .mapResult({predictionAddresses-> Timber.d("$predictionAddresses")
             predictionAddresses.map { PlacePredictionModel(it.id,it.primaryText.toString(),it.secondaryText.toString(),"") }.asResultModel()
                   },{it.toModelError()})

}
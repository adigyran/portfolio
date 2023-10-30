package com.aya.digital.core.feature.profile.address.viewmodel

import com.aya.digital.core.data.base.dataprocessing.dataloading.DataLoadingOperation
import com.aya.digital.core.data.base.dataprocessing.dataloading.enums.OperationState
import com.aya.digital.core.domain.profile.address.GeocodeCoordinatesUseCase
import com.aya.digital.core.domain.profile.address.GetPlacesByAddressQueryUseCase
import com.aya.digital.core.domain.profile.address.GetPlaceByIdUseCase
import com.aya.digital.core.domain.profile.address.GetProfileAddressUseCase
import com.aya.digital.core.domain.profile.address.SaveProfileAddressUseCase
import com.aya.digital.core.domain.profile.address.model.PlaceModel
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.google.android.gms.maps.model.LatLng
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.rx3.asFlow
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProfileAddressViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val geocodeCoordinatesUseCase: GeocodeCoordinatesUseCase,
    private val getPlacesByAddressQueryUseCase: GetPlacesByAddressQueryUseCase,
    private val getProfileAddressUseCase: GetProfileAddressUseCase,
    private val saveProfileAddressUseCase: SaveProfileAddressUseCase,
    private val getPlaceById: GetPlaceByIdUseCase

) :
    BaseViewModel<ProfileAddressState, ProfileAddressSideEffects>() {
    override val container = container<ProfileAddressState, ProfileAddressSideEffects>(
        initialState = ProfileAddressState(),
    )
    {
        getCurrentAddress()
    }

    private fun getCurrentAddress() = intent {
        getProfileAddressUseCase()
            .await()
            .processResult({ address ->
                val location = address.location?.let { LatLng(it.lat, it.lon) }
                reduce {
                    state.copy(selectedAddress = address.addressLine, selectedLocation = location)
                }
                location?.let { postSideEffect(ProfileAddressSideEffects.MoveMap(location)) }
            }, { processError(it) })
    }

    private fun saveCurrentAddress() = intent {
        if (state.selectedAddress.isNullOrBlank()) return@intent
        saveProfileAddressUseCase(addressLine = state.selectedAddress!!)
            .await()
            .processResult({

            }, { processError(it) })
    }

    fun onAddressSuggestionClicked(id: String) = intent {
        getPlaceByPlaceId(id)
    }

    fun querySearch(query: String) {
        queryAddress(query)
    }

    fun onMapClick(latLng: LatLng) {
        geoCodeSelectedCoordinates(latLng)
    }

    fun onSaveClick() {
        saveCurrentAddress()
    }


    private fun queryAddress(query: String) = intent {
        if (state.dataLoadingOperation.isLoading) return@intent
        reduce { state.copy(dataLoadingOperation = DataLoadingOperation.LoadingData(OperationState.PROGRESS)) }
        getPlacesByAddressQueryUseCase(query)
            .subscribeOn(AndroidSchedulers.mainThread())
            .await()
            .processResult({ predictionModels ->
                reduce {
                    state.copy(
                        currentPredictions = predictionModels,
                        dataLoadingOperation = DataLoadingOperation.Idle
                    )
                }
            }, { processError(it) })
    }

    private fun getPlaceByPlaceId(placeId: String) = intent {
        getPlaceById(placeId)
            .subscribeOn(AndroidSchedulers.mainThread())
            .await()
            .processResult({
                renderPlace(it)
            }, { processError(it) })
    }

    private fun renderPlace(placeModel: PlaceModel) = intent {
        if (placeModel.location == null) return@intent
        val placeAddress = placeModel.addressText
        placeModel.location?.let {
            val placeLatLng = LatLng(it.lat, it.lon)

            reduce { state.copy(selectedLocation = placeLatLng, selectedAddress = placeAddress) }
            postSideEffect(ProfileAddressSideEffects.MoveMap(placeLatLng))
        }
        // placeModel.location?.let { reduce { state.copy(selectedLocation = placeModel.location) } }

    }

    private fun geoCodeSelectedCoordinates(latLng: LatLng) = intent {
        if (state.dataLoadingOperation.isLoading) return@intent
        reduce { state.copy(dataLoadingOperation = DataLoadingOperation.LoadingData(OperationState.PROGRESS)) }
        geocodeCoordinatesUseCase(lat = latLng.latitude, latLng.longitude)
            .asFlow()
            .collect { resultModel ->
                resultModel.processResult({ predictionModels ->
                    reduce {
                        state.copy(
                            currentPredictions = predictionModels,
                            dataLoadingOperation = DataLoadingOperation.Idle
                        )
                    }
                }, { processError(it) })
            }
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }


}


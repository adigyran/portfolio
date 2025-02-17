package com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.viewmodel


import com.aya.digital.core.data.base.dataprocessing.dataloading.DataLoadingOperationWithPagination
import com.aya.digital.core.data.base.dataprocessing.dataloading.enums.OperationState
import com.aya.digital.core.data.base.result.models.dictionaries.MultiSelectResultModel
import com.aya.digital.core.data.base.result.models.doctors.SelectDoctorClusterResultModel
import com.aya.digital.core.domain.doctors.base.GetDoctorsUseCase
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.domain.doctors.base.GetDoctorsByCoordinatesUseCase
import com.aya.digital.core.domain.doctors.favourites.AddDoctorToFavoritesUseCase
import com.aya.digital.core.domain.doctors.favourites.GetFavoriteDoctorsUseCase
import com.aya.digital.core.domain.doctors.favourites.RemoveDoctorFromFavoritesUseCase
import com.aya.digital.core.domain.location.GetLocationUseCase
import com.aya.digital.core.domain.location.model.LocationResultModel
import com.aya.digital.core.domain.location.model.ResultModel
import com.aya.digital.core.ext.intersect
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.navigation.DoctorSearchMapNavigationEvents
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.viewmodel.model.SelectedFilterModel
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.rx3.asFlow
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class DoctorSearchMapViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val rootCoordinatorRouter: CoordinatorRouter,
    private val getDoctorsUseCase: GetDoctorsUseCase,
    private val getFavoriteDoctors: GetFavoriteDoctorsUseCase,
    private val addDoctorToFavoritesUseCase: AddDoctorToFavoritesUseCase,
    private val removeDoctorFromFavoritesUseCase: RemoveDoctorFromFavoritesUseCase,
    private val getDoctorsByCoordinatesUseCase: GetDoctorsByCoordinatesUseCase,
    private val getLocationUseCase: GetLocationUseCase
) :
    BaseViewModel<DoctorSearchMapState, DoctorSearchMapSideEffects>() {
    override fun onBack() {
    }

    override val container = container<DoctorSearchMapState, DoctorSearchMapSideEffects>(
        initialState = DoctorSearchMapState(dataOperation = DataLoadingOperationWithPagination.Idle),
    )
    {
        loadFavoriteDoctors()
        loadDoctors()
    }


    fun getLocation() = intent(registerIdling = false) {
        getLocationUseCase().asFlow().collect { result ->
            Timber.d("$result")
            when (result) {
                ResultModel.ShouldGoToSettings -> {

                }

                is ResultModel.ShouldShowRationaleDialog -> {

                }

                is ResultModel.Success -> {
                    processLocationResult(result.result)
                }
            }
        }
    }

    private fun processLocationResult(result: LocationResultModel) = intent {
        when (result) {
            LocationResultModel.CheckLocationSettingsExceptionNotResolved -> {

            }

            LocationResultModel.CoordinatesNotAvailable -> {

            }

            LocationResultModel.LastLocationExceptionNotResolved -> {

            }

            is LocationResultModel.Success -> {
                reduce { state.copy(location = result.location) }
                postSideEffect(DoctorSearchMapSideEffects.OnLocationChanged(result.location))
            }

            is LocationResultModel.UnresolvableException -> {}
        }
    }

    private fun getDoctors(state: DoctorSearchMapState) = getDoctorsByCoordinatesUseCase(
        lat = state.location?.latitude,
        lon = state.location?.longitude
    ).asFlow()


    private fun loadDoctors() = intent(registerIdling = false) {
        if (state.dataOperation.isLoading || state.dataOperation.isNextPageLoading) return@intent
        reduce {
            state.copy(
                cursor = null,
                doctors = null,
                dataOperation = DataLoadingOperationWithPagination.LoadingData(OperationState.PROGRESS)
            )
        }
        getDoctors(state)
            .collect { resultModel ->
                resultModel.processResult({ doctorsPagination ->
                    reduce {
                        state.copy(
                            doctors = doctorsPagination.doctors,
                            cursor = doctorsPagination.cursor,
                            dataOperation = DataLoadingOperationWithPagination.Idle
                        )
                    }
                }, { processError(it) })
            }
    }


    fun onDoctorsClusterClicked(doctorIds: List<Int>) = intent {
        reduce { state.copy(selectedDoctor = null) }
        val selectedDoctors =
            state.doctors?.intersect(doctorIds) { doctorModel, i -> doctorModel.id == i }
        if (selectedDoctors.isNullOrEmpty()) return@intent
        listenForDoctorSelection()
        coordinatorRouter.sendEvent(
            DoctorSearchMapNavigationEvents.OpenDoctorsCluster(
                RequestCodes.DOCTOR_CLUSTER_LIST_REQUEST_CODE,
                selectedDoctors
            )
        )
    }

    private fun listenForDoctorSelection() {
        rootCoordinatorRouter.setResultListener(RequestCodes.DOCTOR_CLUSTER_LIST_REQUEST_CODE) {
           if(it is SelectDoctorClusterResultModel)
           {
               onDoctorClicked(it.doctorId)
           }
        }
    }

    fun onDoctorMarkerClicked(doctorId: Int) = intent {
        reduce { state.copy(selectedDoctor = state.doctors?.firstOrNull { it.id == doctorId }) }
    }

    fun onDoctorClicked(doctorId: Int) = intent {
        coordinatorRouter.sendEvent(DoctorSearchMapNavigationEvents.OpenDoctorCard(doctorId = doctorId))
    }

    fun onMapClick(latLng: LatLng) = intent {
        hideCurrentDoctors()
    }

    private fun hideCurrentDoctors() = intent {
        reduce { state.copy(selectedDoctor = null) }
    }

    fun onInsurance() = intent {
        selectFilters(state.selectedFilters.filterIsInstance<SelectedFilterModel.Insurance>())
    }

    fun onSpecialisation() = intent {
        selectFilters(state.selectedFilters.filterIsInstance<SelectedFilterModel.Speciality>())
    }

    fun onLocation() = intent {
        selectFilters(state.selectedFilters.filterIsInstance<SelectedFilterModel.Location>())
    }

    private inline fun <reified T : SelectedFilterModel> selectFilters(filters: List<T>) {
        when (T::class) {
            SelectedFilterModel.Location::class -> {
                openSelectionScreen(RequestCodes.LOCATIONS_LIST_REQUEST_CODE, filters)
            }

            SelectedFilterModel.Speciality::class -> {
                openSelectionScreen(RequestCodes.SPECIALITIES_LIST_REQUEST_CODE, filters)
            }

            SelectedFilterModel.Insurance::class -> {
                openSelectionScreen(RequestCodes.INSURANCE_LIST_REQUEST_CODE, filters)
            }
        }
    }

    private inline fun <reified T : SelectedFilterModel> openSelectionScreen(
        requestCode: String,
        filters: List<T>
    ) {
        listenForSelectionResult(requestCode)
        val filtersIds = filters.map { it.id }
        val event = when (requestCode) {
            RequestCodes.INSURANCE_LIST_REQUEST_CODE -> {
                DoctorSearchMapNavigationEvents.SelectInsuranceCompanies(
                    requestCode,
                    filtersIds
                )
            }

            RequestCodes.SPECIALITIES_LIST_REQUEST_CODE -> {
                DoctorSearchMapNavigationEvents.SelectSpecialisations(
                    requestCode,
                    filtersIds
                )
            }

            RequestCodes.LOCATIONS_LIST_REQUEST_CODE -> {
                DoctorSearchMapNavigationEvents.SelectLocation(
                    requestCode,
                    filtersIds.firstOrNull()
                )
            }

            else -> {
                DoctorSearchMapNavigationEvents.OpenDoctorCard(1121)
            }
        }
        coordinatorRouter.sendEvent(event)
    }

    private fun listenForSelectionResult(
        requestCode: String
    ) = intent {
        rootCoordinatorRouter.setResultListener(requestCode) { result ->
            if (result !is MultiSelectResultModel) return@setResultListener
            when (requestCode) {
                RequestCodes.LOCATIONS_LIST_REQUEST_CODE -> {
                    val locations =
                        if (result.selectedItems.isEmpty()) listOf() else result.selectedItems.map {
                            SelectedFilterModel.Location(
                                it.id,
                                it.text
                            )
                        }
                    setFilters(locations)
                }

                RequestCodes.INSURANCE_LIST_REQUEST_CODE -> {
                    val insurances =
                        if (result.selectedItems.isEmpty()) listOf<SelectedFilterModel.Insurance>()
                        else result.selectedItems.map {
                            SelectedFilterModel.Insurance(
                                it.id,
                                it.text
                            )
                        }
                    setFilters(insurances)
                }

                RequestCodes.SPECIALITIES_LIST_REQUEST_CODE -> {
                    val specialities =
                        if (result.selectedItems.isEmpty()) listOf<SelectedFilterModel.Speciality>()
                        else result.selectedItems.map {
                            SelectedFilterModel.Speciality(
                                it.id,
                                it.text
                            )
                        }
                    setFilters(specialities)
                }

                else -> {
                    listOf<SelectedFilterModel>()
                }
            }
        }
    }

    private inline fun <reified T : SelectedFilterModel> setFilters(filterItems: List<T>) = intent {
        Timber.d("${T::class}")
        val filters = state.selectedFilters.toMutableSet().apply {

            removeAll {
                Timber.d("${it::class} ${T::class}")
                it::class == T::class
            }
            addAll(filterItems)
        }
        reduce { state.copy(selectedFilters = filters.toSet()) }
    }

    fun onRefreshDoctors() = intent {
        loadDoctors()
    }

    fun findDoctorClicked() = intent {
        loadDoctors()
    }

    fun onDoctorFavouriteClicked(doctorId: Int) = intent {
        toggleDoctorFavourite(doctorId)
    }

    private fun toggleDoctorFavourite(doctorId: Int) = intent {
        if (state.favoriteDoctors?.firstOrNull { it == doctorId } != null) {
            removeDoctorFromFavoritesUseCase(doctorId).await()
                .processResult({
                    loadFavoriteDoctors()
                }, { processError(it) })
        } else {
            addDoctorToFavoritesUseCase(doctorId).await()
                .processResult({ loadFavoriteDoctors() }, { processError(it) })
        }
    }

    private fun loadFavoriteDoctors() = intent(registerIdling = false) {
        getFavoriteDoctors().asFlow()
            .collect { resultModel ->
                resultModel.processResult({ doctors ->
                    reduce {
                        state.copy(
                            favoriteDoctors = doctors,
                        )
                    }
                }, { processError(it) })
            }
    }


}


package com.aya.digital.core.feature.tabviews.doctorsearchcontainer.viewmodel


import com.aya.digital.core.data.base.dataprocessing.dataloading.DataLoadingOperationWithPagination
import com.aya.digital.core.data.base.dataprocessing.dataloading.enums.OperationState
import com.aya.digital.core.data.base.result.models.dictionaries.MultiSelectResultModel
import com.aya.digital.core.domain.doctors.base.GetDoctorsUseCase
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.domain.doctors.favourites.AddDoctorToFavoritesUseCase
import com.aya.digital.core.domain.doctors.favourites.GetFavoriteDoctorsUseCase
import com.aya.digital.core.domain.doctors.favourites.RemoveDoctorFromFavoritesUseCase
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.CurrentMode
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.navigation.DoctorSearchContainerNavigationEvents
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.viewmodel.model.SelectedFilterModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class DoctorSearchContainerViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val rootCoordinatorRouter: CoordinatorRouter,
    private val getDoctorsUseCase: GetDoctorsUseCase,
    private val getFavoriteDoctors: GetFavoriteDoctorsUseCase,
    private val addDoctorToFavoritesUseCase: AddDoctorToFavoritesUseCase,
    private val removeDoctorFromFavoritesUseCase: RemoveDoctorFromFavoritesUseCase
) :
    BaseViewModel<DoctorSearchContainerState, DoctorSearchContainerSideEffects>() {

    override val container = container<DoctorSearchContainerState, DoctorSearchContainerSideEffects>(
        initialState = DoctorSearchContainerState(dataOperation = DataLoadingOperationWithPagination.Idle),
    )
    {
        loadFavoriteDoctors()
        loadDoctors()
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }


    fun toggleMode() = intent {
        val currentMode = when (state.currentMode) {
            CurrentMode.List -> {
                CurrentMode.Map
            }

            CurrentMode.Map -> {
                CurrentMode.List
            }
        }
        reduce { state.copy(currentMode = currentMode) }
    }

    private fun getDoctors(state: DoctorSearchContainerState) = getDoctorsUseCase(
        cursor = state.cursor,
        cities = state.selectedFilters.filterIsInstance<SelectedFilterModel.Location>()
            .map { it.name },
        specialities = state.selectedFilters.filterIsInstance<SelectedFilterModel.Speciality>()
            .map { it.id },
        insurances = state.selectedFilters.filterIsInstance<SelectedFilterModel.Insurance>()
            .map { it.id },
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


    fun onDoctorClicked(doctorId: Int) = intent {
        coordinatorRouter.sendEvent(DoctorSearchContainerNavigationEvents.OpenDoctorCard(doctorId = doctorId))
    }

    fun loadNextPage() = intent {
        if (state.dataOperation.isLoading || state.dataOperation.isNextPageLoading) return@intent
        if (state.cursor.isNullOrBlank()) return@intent
        reduce {
            state.copy(
                dataOperation = DataLoadingOperationWithPagination.NextPageLoading(
                    OperationState.PROGRESS
                )
            )
        }
        getDoctors(state)
            .collect { resultModel ->
                resultModel.processResult({ doctorsPagination ->
                    reduce {
                        val resultDoctors = addDoctors(state.doctors, doctorsPagination.doctors)
                        state.copy(
                            doctors = resultDoctors,
                            cursor = doctorsPagination.cursor,
                            dataOperation = (DataLoadingOperationWithPagination.Idle)
                        )
                    }
                }, { processError(it) })
            }
    }

    private fun addDoctors(oldDoctors: List<DoctorModel>?, newDoctors: List<DoctorModel>) =
        mutableListOf<DoctorModel>()
            .apply {
                oldDoctors?.run { addAll(this) }
                newDoctors.run { addAll(this) }
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
                DoctorSearchContainerNavigationEvents.SelectInsuranceCompanies(
                    requestCode,
                    filtersIds
                )
            }

            RequestCodes.SPECIALITIES_LIST_REQUEST_CODE -> {
                DoctorSearchContainerNavigationEvents.SelectSpecialisations(
                    requestCode,
                    filtersIds
                )
            }

            RequestCodes.LOCATIONS_LIST_REQUEST_CODE -> {
                DoctorSearchContainerNavigationEvents.SelectLocation(
                    requestCode,
                    filtersIds.firstOrNull()
                )
            }

            else -> {
                DoctorSearchContainerNavigationEvents.OpenDoctorCard(1121)
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


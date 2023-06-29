package com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel


import com.aya.digital.core.data.base.dataprocessing.dataloading.enums.OperationState
import com.aya.digital.core.data.base.result.models.dictionaries.MultiSelectResultModel
import com.aya.digital.core.domain.doctors.base.GetDoctorsUseCase
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.domain.doctors.favourites.AddDoctorToFavoritesUseCase
import com.aya.digital.core.domain.doctors.favourites.RemoveDoctorFromFavoritesUseCase
import com.aya.digital.core.feature.tabviews.doctorsearch.navigation.DoctorSearchNavigationEvents
import com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel.model.SelectedFilterModel
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.reactive.asFlow
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber
import com.aya.digital.core.data.base.dataprocessing.dataloading.DataLoadingOperationWithPagination as DataLoadingOperation

class DoctorSearchViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val rootCoordinatorRouter: CoordinatorRouter,
    private val getDoctorsUseCase: GetDoctorsUseCase,
    private val addDoctorToFavoritesUseCase: AddDoctorToFavoritesUseCase,
    private val removeDoctorFromFavoritesUseCase: RemoveDoctorFromFavoritesUseCase
) :
    BaseViewModel<DoctorSearchState, DoctorSearchSideEffects>() {
    override fun onBack() {
        TODO("Not yet implemented")
    }

    override val container = container<DoctorSearchState, DoctorSearchSideEffects>(
        initialState = DoctorSearchState(dataOperation = DataLoadingOperation.Idle),
    )
    {
        loadDoctors()
    }



    private fun getDoctors(state: DoctorSearchState) = getDoctorsUseCase(
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
                dataOperation = DataLoadingOperation.LoadingData(OperationState.PROGRESS)
            )
        }
        getDoctors(state)
            .collect { resultModel ->
                resultModel.processResult({ doctorsPagination ->
                    reduce {
                        state.copy(
                            doctors = doctorsPagination.doctors,
                            cursor = doctorsPagination.cursor,
                            dataOperation = DataLoadingOperation.Idle
                        )
                    }
                }, { processError(it) })
            }
    }


    fun onDoctorClicked(doctorId: Int) = intent {
        coordinatorRouter.sendEvent(DoctorSearchNavigationEvents.OpenDoctorCard(doctorId = doctorId))
    }

    fun loadNextPage() = intent {
        if (state.dataOperation.isLoading || state.dataOperation.isNextPageLoading) return@intent
        if (state.cursor.isNullOrBlank()) return@intent
        reduce {
            state.copy(dataOperation = DataLoadingOperation.NextPageLoading(OperationState.PROGRESS))
        }
        getDoctors(state)
            .collect { resultModel ->
                resultModel.processResult({ doctorsPagination ->
                    reduce {
                        val resultDoctors = addDoctors(state.doctors, doctorsPagination.doctors)
                        state.copy(
                            doctors = resultDoctors,
                            cursor = doctorsPagination.cursor,
                            dataOperation = (DataLoadingOperation.Idle)
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
                DoctorSearchNavigationEvents.SelectInsuranceCompanies(
                    requestCode,
                    filtersIds
                )
            }

            RequestCodes.SPECIALITIES_LIST_REQUEST_CODE -> {
                DoctorSearchNavigationEvents.SelectSpecialisations(
                    requestCode,
                    filtersIds
                )
            }

            RequestCodes.LOCATIONS_LIST_REQUEST_CODE -> {
                DoctorSearchNavigationEvents.SelectLocation(
                    requestCode,
                    filtersIds.firstOrNull()
                )
            }

            else -> {
                DoctorSearchNavigationEvents.OpenDoctorCard(1121)
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

    fun onDoctorFavouriteClicked(doctorId: Int) = intent {  }

    private fun toggleDoctorFavourite(doctorId: Int) = intent {

    }
}


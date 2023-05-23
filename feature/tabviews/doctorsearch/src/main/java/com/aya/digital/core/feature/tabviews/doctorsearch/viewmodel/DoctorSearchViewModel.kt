package com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel


import com.aya.digital.core.data.base.dataprocessing.dataloading.enums.OperationState
import com.aya.digital.core.domain.doctors.base.GetDoctorsUseCase
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.feature.tabviews.doctorsearch.navigation.DoctorSearchNavigationEvents
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.reactive.asFlow
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import com.aya.digital.core.data.base.dataprocessing.dataloading.DataLoadingOperationWithPagination as DataLoadingOperation

class DoctorSearchViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val getDoctorsUseCase: GetDoctorsUseCase
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

    private fun loadDoctors() = intent(registerIdling = false) {
        if(state.dataOperation.isLoading || state.dataOperation.isNextPageLoading) return@intent
        reduce {
            state.copy(
                cursor = null,
                doctors = null,
                dataOperation = DataLoadingOperation.LoadingData(OperationState.PROGRESS)
            )
        }
        getDoctorsUseCase(state.cursor).asFlow()
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
        if(state.dataOperation.isLoading || state.dataOperation.isNextPageLoading) return@intent
        if(state.cursor.isNullOrBlank()) return@intent
        reduce {
            state.copy(dataOperation = DataLoadingOperation.NextPageLoading(OperationState.PROGRESS))
        }
        getDoctorsUseCase(state.cursor).asFlow()
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

    private fun addDoctors(oldDoctors: List<com.aya.digital.core.domain.base.models.doctors.DoctorModel>?, newDoctors: List<com.aya.digital.core.domain.base.models.doctors.DoctorModel>) =
        mutableListOf<com.aya.digital.core.domain.base.models.doctors.DoctorModel>()
            .apply {
                oldDoctors?.run { addAll(this) }
                newDoctors.run { addAll(this) }
            }

    fun onRefreshDoctors() = intent {

        loadDoctors()
    }

    fun findDoctorClicked() = intent {
        coordinatorRouter.sendEvent(DoctorSearchNavigationEvents.OpenDoctorCard(doctorId = 1284))
    }

}


package com.aya.digital.core.feature.auth.signin.viewmodel


import com.aya.digital.core.domain.home.lastupdates.GetLastUpdatesUseCase
import com.aya.digital.core.feature.tabviews.home.navigation.HomeNavigationEvents
import com.aya.digital.core.feature.tabviews.home.viewmodel.HomeSideEffects
import com.aya.digital.core.feature.tabviews.home.viewmodel.HomeState
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class HomeViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val getLastUpdatesUseCase: GetLastUpdatesUseCase
) :
    BaseViewModel<HomeState, HomeSideEffects>() {
    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }

    override val container = container<HomeState, HomeSideEffects>(
        initialState = HomeState(""),
    )
    {
        getLatestUpdates()
    }

    private fun getLatestUpdates() = intent {
        getLastUpdatesUseCase().await()
            .processResult({map->
                reduce {
                    state.copy(
                        lastUpdates = map
                    )
                }
            },{processError(it)})
    }

    fun openDoctors() = intent {
        coordinatorRouter.sendEvent(HomeNavigationEvents.FindDoctor)
    }

    fun openAppointments() = intent {
        coordinatorRouter.sendEvent(HomeNavigationEvents.ShowAppointments)
    }

}


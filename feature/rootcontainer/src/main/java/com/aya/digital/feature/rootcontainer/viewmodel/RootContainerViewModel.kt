package com.aya.digital.feature.rootcontainer.viewmodel

import android.util.Log
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.auth.CheckIsAuthenticatedUseCase
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.events.invalidToken.InvalidTokenEventManager
import com.aya.digital.core.util.requestcodes.RequestCodes
import com.aya.digital.feature.rootcontainer.navigation.RootContainerNavigationEvents
import com.aya.digital.feature.rootcontainer.ui.RootView
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class RootContainerViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val checkIsAuthenticatedUseCase: CheckIsAuthenticatedUseCase,
    private val invalidTokenEventManager: InvalidTokenEventManager
) : BaseViewModel<RootContainerState, BaseSideEffect>() {

    override val container = container<RootContainerState, BaseSideEffect>(
        initialState = RootContainerState,
    )
    {
    }
    fun openDefaultScreen() = intent {
        val isAuthenticatedResult = checkIsAuthenticatedUseCase().await()
        isAuthenticatedResult.processResult({ authenticated ->
            if (authenticated) coordinatorRouter.sendEvent(RootContainerNavigationEvents.OpenDefaultScreenAuthorized)
            else coordinatorRouter.sendEvent(RootContainerNavigationEvents.OpenDefaultScreen)
        }, { Timber.e((it as RequestResultModel.Error.Another).throwable) })
    }

    fun listenForToken() = intent {
        invalidTokenEventManager.subscribeToEvent().subscribe{
            if(it) coordinatorRouter.sendEvent(RootContainerNavigationEvents.OpenDefaultScreen)
        }
    }
}

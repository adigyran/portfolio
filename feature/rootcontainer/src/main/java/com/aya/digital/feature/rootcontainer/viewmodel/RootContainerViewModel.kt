package com.aya.digital.feature.rootcontainer.viewmodel

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.auth.signin.CheckIsAuthenticatedUseCase
import com.aya.digital.core.domain.root.progress.ListenForProgressUseCase
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.events.invalidToken.InvalidTokenEventManager
import com.aya.digital.feature.rootcontainer.navigation.RootContainerNavigationEvents
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.rx3.asFlow
import kotlinx.coroutines.rx3.await
import kotlinx.coroutines.rx3.awaitLast
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class RootContainerViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val checkIsAuthenticatedUseCase: CheckIsAuthenticatedUseCase,
    private val listenForProgressUseCase: ListenForProgressUseCase,
    private val invalidTokenEventManager: InvalidTokenEventManager
) : BaseViewModel<RootContainerState, BaseSideEffect>() {
    override fun onBack() {
        TODO("Not yet implemented")
    }

    override val container = container<RootContainerState, BaseSideEffect>(
        initialState = RootContainerState(),
    )
    {
        listenForProgress()
    }

    fun openDefaultScreen() = intent {
        val isAuthenticatedResult = checkIsAuthenticatedUseCase().await()
        isAuthenticatedResult.processResult({ authenticated ->
            if (authenticated) coordinatorRouter.sendEvent(RootContainerNavigationEvents.OpenDefaultScreenAuthorized)
            else coordinatorRouter.sendEvent(RootContainerNavigationEvents.OpenDefaultScreen)
        }, {
            Timber.e((it as RequestResultModel.Error.Another).throwable)
            sendExceptionToCrashlytics(it.throwable)
        })
    }

    fun listenForToken() = intent {
        invalidTokenEventManager.subscribeToEvent().subscribe {
            if (it) coordinatorRouter.sendEvent(RootContainerNavigationEvents.OpenDefaultScreen)
        }
    }

    private fun listenForProgress() = intent {
        listenForProgressUseCase().asFlow()
            .collect { progress ->
                Timber.d("progress $progress")
                reduce { state.copy(inProgress = progress) } }
    }
}

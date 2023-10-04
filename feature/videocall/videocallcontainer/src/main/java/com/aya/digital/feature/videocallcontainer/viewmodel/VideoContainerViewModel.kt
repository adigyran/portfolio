package com.aya.digital.feature.videocallcontainer.viewmodel

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.auth.signin.CheckIsAuthenticatedUseCase
import com.aya.digital.core.domain.root.progress.ListenForProgressUseCase
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.events.invalidToken.InvalidTokenEventManager
import com.aya.digital.feature.videocallcontainer.navigation.VideoContainerNavigationEvents
import com.aya.digital.feature.videocallcontainer.ui.VideoContainerView
import kotlinx.coroutines.rx3.asFlow
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class VideoContainerViewModel(
    private val param: VideoContainerView.Param,
    private val coordinatorRouter: CoordinatorRouter,
    private val checkIsAuthenticatedUseCase: CheckIsAuthenticatedUseCase,
    private val listenForProgressUseCase: ListenForProgressUseCase,
    private val invalidTokenEventManager: InvalidTokenEventManager
) : BaseViewModel<VideoContainerState, BaseSideEffect>() {
    override fun onBack() {
        TODO("Not yet implemented")
    }

    override val container = container<VideoContainerState, BaseSideEffect>(
        initialState = VideoContainerState(),
    )
    {
        listenForProgress()
    }

    fun openDefaultScreen() = intent {
        coordinatorRouter.sendEvent(VideoContainerNavigationEvents.OpenVideoCall(param.roomId))
    }
    fun listenForToken() = intent {
        invalidTokenEventManager.subscribeToEvent().subscribe {
            if (it) coordinatorRouter.sendEvent(CoordinatorEvent.Back)
        }
    }


    private fun listenForProgress() = intent(registerIdling = false) {
        listenForProgressUseCase().asFlow()
            .collect { progress ->
                Timber.d("progress $progress")
                reduce { state.copy(inProgress = progress) } }
    }
}

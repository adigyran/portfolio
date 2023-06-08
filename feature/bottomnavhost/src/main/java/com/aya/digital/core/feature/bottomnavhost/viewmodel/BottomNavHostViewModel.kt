package com.aya.digital.core.feature.bottomnavhost.viewmodel

import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileAvatarUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileBriefUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileInfoUseCase
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class BottomNavHostViewModel(
    val coordinatorRouter: CoordinatorRouter,
    private val getProfileAvatarUseCase: GetProfileAvatarUseCase
) :
    BaseViewModel<BottomNavHostState, BaseSideEffect>() {
    override fun onBack() {
        TODO("Not yet implemented")
    }

    override val container = container<BottomNavHostState, BaseSideEffect>(
        initialState = BottomNavHostState(),
    )
    {
        getProfileAvatar()
    }

    private fun getProfileAvatar() = intent {
        getProfileAvatarUseCase().await()
            .processResult({ avatarUrl ->
                reduce { state.copy(avatarUrl = avatarUrl) }
            }, { processError(it) })
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) {
        super.postErrorSideEffect(errorSideEffect)
    }
}
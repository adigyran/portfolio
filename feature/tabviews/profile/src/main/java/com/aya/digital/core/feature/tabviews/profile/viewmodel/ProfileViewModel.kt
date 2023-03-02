package com.aya.digital.core.feature.tabviews.profile.viewmodel

import com.aya.digital.core.domain.profile.GetProfileBriefUseCase
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class ProfileViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val getProfileUseCase: GetProfileBriefUseCase
) :
    BaseViewModel<ProfileState, BaseSideEffect>() {
    override val container = container<ProfileState, BaseSideEffect>(
        initialState = ProfileState(""),
    )
    {
        loadProfile()
    }

    init {
        Timber.d("ProfileViewModel Init")
    }

    private fun loadProfile() = intent {
        val profile =  getProfileUseCase().await()
        profile.processResult({
                              Timber.d(it.toString())
        },{Timber.e(it.toString())})
    }


}


package com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel

import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileInfoUseCase
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class ProfileGeneralInfoEditViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val profileInfoUseCase: GetProfileInfoUseCase
    ) :
    BaseViewModel<ProfileGeneralInfoEditState, BaseSideEffect>() {
    override val container = container<ProfileGeneralInfoEditState, BaseSideEffect>(
        initialState = ProfileGeneralInfoEditState(),
    )
    {

    }

    init {
        loadProfile()
    }

    private fun loadProfile() = intent {
        val resultModel = profileInfoUseCase().await()
        resultModel.processResult({ profileInfo ->
            reduce {
                state.copy(
                    firstName = profileInfo.firstName,
                    middleName = profileInfo.middleName,
                    lastName = profileInfo.lastName,
                    shortAddress = profileInfo.shortAddress,
                    dateOfBirth = profileInfo.dateOfBirth,
                    height = profileInfo.height,
                    sex = profileInfo.sex,
                    weight = profileInfo.weight

                )
            }
        }, { Timber.d(it.toString()) })

    }

    fun onSaveProfileClicked() = intent {

    }


}


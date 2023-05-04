package com.aya.digital.core.feature.profile.notifications.viewmodel

import com.aya.digital.core.domain.profile.notifications.GetEmailNotificationsStatusUseCase
import com.aya.digital.core.domain.profile.notifications.SetEmailNotificationsStatusUseCase
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class ProfileNotificationsViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val getEmailNotificationsStatusUseCase: GetEmailNotificationsStatusUseCase,
    private val setEmailNotificationsStatusUseCase: SetEmailNotificationsStatusUseCase
) :
    BaseViewModel<ProfileNotificationsState, BaseSideEffect>() {
    override val container = container<ProfileNotificationsState, BaseSideEffect>(
        initialState = ProfileNotificationsState(),
    )
    {
        getInitialData()
    }


    private fun getInitialData() = intent {
        val await = getEmailNotificationsStatusUseCase().await()
        await.processResult({
            reduce {
                state.copy(emailNotification = it)
            }
        }, { Timber.d(it.toString()) })
    }

    fun onEmailNotificationSwitched(value: Boolean) = intent {
        reduce { state.copy(emailNotification = value) }
        setEmailNotificationsStatus()
    }

    private fun setEmailNotificationsStatus() = intent {
        val await = setEmailNotificationsStatusUseCase(state.emailNotification).await()
        await.processResult({}, { Timber.d(it.toString()) })
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }

}


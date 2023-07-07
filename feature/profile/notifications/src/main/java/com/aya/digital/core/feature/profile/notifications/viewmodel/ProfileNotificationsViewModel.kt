package com.aya.digital.core.feature.profile.notifications.viewmodel

import com.aya.digital.core.domain.profile.notifications.GetNotificationsStatusUseCase
import com.aya.digital.core.domain.profile.notifications.SetNotificationsStatusUseCase
import com.aya.digital.core.domain.profile.notifications.model.NotificationsStatusModel
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
    private val getEmailNotificationsStatusUseCase: GetNotificationsStatusUseCase,
    private val setEmailNotificationsStatusUseCase: SetNotificationsStatusUseCase
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
                state.copy(
                    emailNotification = it.emailNotifications,
                    smsNotification = it.smsNotifications
                )
            }
        }, { Timber.d(it.toString()) })
    }

    fun onEmailNotificationSwitched(value: Boolean) = intent {
        reduce { state.copy(emailNotification = value) }
        setNotificationsStatus()
    }

    fun onSmsNotificationSwitched(value: Boolean) = intent {
        reduce { state.copy(smsNotification = value) }
        setNotificationsStatus()
    }

    private fun setNotificationsStatus() = intent {
        val await = setEmailNotificationsStatusUseCase(
            NotificationsStatusModel(
                state.emailNotification,
                state.smsNotification
            )
        ).await()
        await.processResult({}, { Timber.d(it.toString()) })
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }

}


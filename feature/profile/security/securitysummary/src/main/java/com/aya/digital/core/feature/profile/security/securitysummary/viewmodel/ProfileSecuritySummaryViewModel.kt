package com.aya.digital.core.feature.profile.security.securitysummary.viewmodel

import com.aya.digital.core.domain.profile.security.summary.GetSecuritySummaryUseCase
import com.aya.digital.core.feature.profile.security.securitysummary.FieldsTags
import com.aya.digital.core.feature.profile.security.securitysummary.navigation.ProfileSecuritySummaryNavigationEvents
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

internal class ProfileSecuritySummaryViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val getSecuritySummaryUseCase: GetSecuritySummaryUseCase,
) :
    BaseViewModel<ProfileSecuritySummaryState, ProfileSecuritySummarySideEffects>() {
    override val container = container<ProfileSecuritySummaryState, ProfileSecuritySummarySideEffects>(
        initialState = ProfileSecuritySummaryState(),
    )
    {

    }

    init {
        getSecuritySummary()
    }

    private fun getSecuritySummary() = intent {
        val await = getSecuritySummaryUseCase().await()
        await.processResult({
            reduce { state.copy(email = it.email, password = it.password) }
        }, { processError(it) })
    }

    fun itemClicked(tag: Int) = intent {
        coordinatorRouter.sendEvent(
            when (tag) {
                FieldsTags.EMAIL_FIELD_TAG -> ProfileSecuritySummaryNavigationEvents.ChangeEmail(RequestCodes.CHANGE_EMAIL_REQUEST_CODE)
                FieldsTags.PASSWORD_FIELD_TAG -> ProfileSecuritySummaryNavigationEvents.ChangePassword(RequestCodes.CHANGE_PASSWORD_REQUEST_CODE)
                else -> {
                    ProfileSecuritySummaryNavigationEvents.ChangeEmail(RequestCodes.CHANGE_EMAIL_REQUEST_CODE)
                }
            }
        )
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) = intent {
        postSideEffect(ProfileSecuritySummarySideEffects.Error(errorSideEffect))
    }
}


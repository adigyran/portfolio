package com.aya.digital.core.feature.tabviews.profile.viewmodel

import com.aya.digital.core.domain.profile.BriefProfileModel
import com.aya.digital.core.domain.profile.GetProfileBriefUseCase
import com.aya.digital.core.feature.tabviews.profile.FieldsTags
import com.aya.digital.core.feature.tabviews.profile.navigation.ProfileNavigationEvents
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
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
        val profile = getProfileUseCase().await()
        profile.processResult({
            processBriefProfile(it)
        }, { Timber.e(it.toString()) })
    }

    private fun processBriefProfile(profile: BriefProfileModel) = intent {
        reduce {
            state.copy(
                firstName = profile.firstName,
                lastName = profile.lastName,
                avatar = null
            )
        }
    }


    fun onProfileButtonClicked(buttonTag: Int) = intent {
        Timber.d(buttonTag.toString())
        coordinatorRouter.sendEvent(when(buttonTag)
        {
            FieldsTags.GENERAL_INFO_BUTTON_TAG -> ProfileNavigationEvents.OpenProfileGeneralInfo
            FieldsTags.EMERGENCY_CONTACT_BUTTON_TAG -> ProfileNavigationEvents.OpenProfileEmergencyContact
            FieldsTags.SECURITY_BUTTON_TAG -> ProfileNavigationEvents.OpenProfileSecurity
            FieldsTags.INSURANCE_BUTTON_TAG -> ProfileNavigationEvents.OpenProfileInsurance
            FieldsTags.NOTIFICATION_BUTTON_TAG -> ProfileNavigationEvents.OpenProfileNotification
            else -> ProfileNavigationEvents.OpenProfileGeneralInfo
        })

    }

}


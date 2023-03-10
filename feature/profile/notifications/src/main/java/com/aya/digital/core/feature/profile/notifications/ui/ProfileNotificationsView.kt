package com.aya.digital.core.feature.profile.notifications.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.feature.profile.security.notifications.databinding.ViewProfileNotificationsBinding
import com.aya.digital.core.feature.profile.notifications.di.profileNotificationsDiModule
import com.aya.digital.core.feature.profile.notifications.ui.model.ProfileNotificationsStateTransformer
import com.aya.digital.core.feature.profile.notifications.ui.model.ProfileNotificationsUiModel
import com.aya.digital.core.feature.profile.notifications.viewmodel.ProfileNotificationsState
import com.aya.digital.core.feature.profile.notifications.viewmodel.ProfileNotificationsViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.base.screens.DiFragment
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class ProfileNotificationsView :
    DiFragment<ViewProfileNotificationsBinding, ProfileNotificationsViewModel, ProfileNotificationsState, BaseSideEffect, ProfileNotificationsUiModel, ProfileNotificationsStateTransformer>() {

    private val viewModelFactory: ((Unit) -> ProfileNotificationsViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileNotificationsStateTransformer) by kodein.on(
        context = this
    ).factory()

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
        binding.smsNotificationSw.setOnCheckedChangeListener { compoundButton, b ->
            viewModel.onEmailNotificationSwitched(b)
        }
    }

    override fun provideDiModule(): DI.Module = profileNotificationsDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileNotificationsBinding =
        ViewProfileNotificationsBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) {
        super.sideEffect(sideEffect)
    }

    override fun render(state: ProfileNotificationsState) {
        stateTransformer(state).run {
            if (binding.smsNotificationSw.isChecked != this.emailNotificationsState) {
                binding.smsNotificationSw.isChecked = this.emailNotificationsState
            }
        }
    }

    override fun provideViewModel(): ProfileNotificationsViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfileNotificationsStateTransformer =
        stateTransformerFactory(Unit)

}

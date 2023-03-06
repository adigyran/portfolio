package com.aya.digital.core.feature.profile.notifications.ui

import android.os.Bundle
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.feature.profile.security.notifications.databinding.ViewProfileNotificationsBinding
import com.aya.digital.core.feature.profile.notifications.di.profileNotificationsDiModule
import com.aya.digital.core.feature.profile.notifications.ui.model.ProfileNotificationsStateTransformer
import com.aya.digital.core.feature.profile.notifications.ui.model.ProfileNotificationsUiModel
import com.aya.digital.core.feature.profile.notifications.viewmodel.ProfileNotificationsState
import com.aya.digital.core.feature.profile.notifications.viewmodel.ProfileNotificationsViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.ext.SpannableObject
import com.aya.digital.core.ui.base.ext.colors
import com.aya.digital.core.ui.base.ext.createSpannableText
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
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

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {


        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
    }

    override fun provideDiModule(): DI.Module = profileNotificationsDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileNotificationsBinding = ViewProfileNotificationsBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: ProfileNotificationsState) {
        stateTransformer(state).data?.let {

        }
    }

    override fun provideViewModel(): ProfileNotificationsViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfileNotificationsStateTransformer =
        stateTransformerFactory(Unit)

}

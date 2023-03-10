package com.aya.digital.core.feature.tabviews.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.dpToPx

import com.aya.digital.core.feature.tabviews.profile.ui.model.ProfileStateTransformer
import com.aya.digital.core.feature.tabviews.profile.ui.model.ProfileUiModel
import com.aya.digital.core.feature.tabviews.profile.viewmodel.ProfileState
import com.aya.digital.core.feature.tabviews.profile.viewmodel.ProfileViewModel
import com.aya.digital.core.feature.tabviews.profile.databinding.ViewProfileBinding
import com.aya.digital.core.feature.tabviews.profile.di.profileDiModule
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.delegates.profile.info.ui.ProfileMainDelegateListeners
import com.aya.digital.core.ui.delegates.profile.info.ui.profileMainDelegate
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class ProfileView :
    DiFragment<ViewProfileBinding, ProfileViewModel, ProfileState, BaseSideEffect, ProfileUiModel, ProfileStateTransformer>() {

    private val viewModelFactory: ((Unit) -> ProfileViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate { profileMainDelegate(ProfileMainDelegateListeners(viewModel::onProfileButtonClicked)) }
        }
    }


    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
        if (savedInstanceState == null) {
            recyclers.add(binding.recycler)
            with(binding.recycler) {
                itemAnimator = null
                setHasFixedSize(true)
                setItemViewCacheSize(30)
                isNestedScrollingEnabled = false

                val lm = LinearLayoutManager(
                    context,
                    RecyclerView.VERTICAL,
                    false
                )
                layoutManager = lm
            }
        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
    }

    override fun provideDiModule(): DI.Module = profileDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileBinding = ViewProfileBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) {
        super.sideEffect(sideEffect)
    }

    override fun render(state: ProfileState) {
        stateTransformer(state).run {
            data?.let {
                adapter.items = it
                if (binding.recycler.adapter == null) {
                    binding.recycler.swapAdapter(adapter, true)
                }
            }
            name?.let {
                binding.nameTv.text = it
            }
            address?.let {
                binding.addressTv.text = it
            }
            setAvatar(avatar)
        }
    }

    private fun setAvatar(avatar: String?) {
        avatar?.let {
            Glide
                .with(binding.avatarIv)
                .load(it)
                .transform(
                    CenterCrop(),
                    RoundedCorners(10.dpToPx())
                )
                .dontAnimate()
                .into(binding.avatarIv)
        }
    }

    override fun provideViewModel(): ProfileViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfileStateTransformer =
        stateTransformerFactory(Unit)

}

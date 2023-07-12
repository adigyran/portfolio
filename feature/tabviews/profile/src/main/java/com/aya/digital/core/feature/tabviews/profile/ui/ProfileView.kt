package com.aya.digital.core.feature.tabviews.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.aya.digital.core.feature.tabviews.profile.ui.model.ProfileStateTransformer
import com.aya.digital.core.feature.tabviews.profile.ui.model.ProfileUiModel
import com.aya.digital.core.feature.tabviews.profile.viewmodel.ProfileState
import com.aya.digital.core.feature.tabviews.profile.viewmodel.ProfileViewModel
import com.aya.digital.core.feature.tabviews.profile.databinding.ViewProfileBinding
import com.aya.digital.core.feature.tabviews.profile.di.profileDiModule
import com.aya.digital.core.feature.tabviews.profile.viewmodel.ProfileSideEffects
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.delegates.profile.main.ui.ProfileMainDelegate
import com.aya.digital.core.ui.delegates.profile.main.ui.ProfileMainDelegateListeners
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class ProfileView :
    DiFragment<ViewProfileBinding, ProfileViewModel, ProfileState, ProfileSideEffects, ProfileUiModel, ProfileStateTransformer>() {

    private val viewModelFactory: ((Unit) -> ProfileViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate { ProfileMainDelegate(ProfileMainDelegateListeners(viewModel::onProfileButtonClicked)) }
        }
    }


    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
        binding.toolbar.title.text = "Profile"
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
            addItemDecoration(ProfileTabDecoration())
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

    override fun sideEffect(sideEffect: ProfileSideEffects) =
        when (sideEffect) {
            is ProfileSideEffects.Error -> processErrorSideEffect(sideEffect.error)
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
                binding.toolbar.nameTv.text = it
            }
            age?.let {
                binding.toolbar.ageTv.text = it
            }
            setAvatar(avatar)
        }
    }

    private fun setAvatar(avatar: String?) {
        avatar?.let {
            Glide
                .with(binding.toolbar.avatar)
                .load(it)
                .transform(
                    CircleCrop()
                )
                .dontAnimate()
                .into(binding.toolbar.avatar)
        }
    }

    override fun provideViewModel(): ProfileViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfileStateTransformer =
        stateTransformerFactory(Unit)

}

package com.aya.digital.core.feature.profile.generalinfo.view.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.feature.profile.generalinfo.view.databinding.ViewProfileGeneralinfoViewBinding
import com.aya.digital.core.feature.profile.generalinfo.view.di.profileGeneralInfoViewDiModule
import com.aya.digital.core.feature.profile.generalinfo.view.ui.contract.ProfileGeneralInfoEditAvatarImageSelectContract
import com.aya.digital.core.feature.profile.generalinfo.view.ui.model.ProfileGeneralInfoViewStateTransformer
import com.aya.digital.core.feature.profile.generalinfo.view.ui.model.ProfileGeneralInfoViewUiModel
import com.aya.digital.core.feature.profile.generalinfo.view.viewmodel.ProfileGeneralInfoSideEffects
import com.aya.digital.core.feature.profile.generalinfo.view.viewmodel.ProfileGeneralInfoViewState
import com.aya.digital.core.feature.profile.generalinfo.view.viewmodel.ProfileGeneralInfoViewViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.delegates.profile.info.ui.ProfileInfoDelegate
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class ProfileGeneralInfoViewView :
    DiFragment<ViewProfileGeneralinfoViewBinding, ProfileGeneralInfoViewViewModel, ProfileGeneralInfoViewState, ProfileGeneralInfoSideEffects, ProfileGeneralInfoViewUiModel, ProfileGeneralInfoViewStateTransformer>() {

    private val viewModelFactory: ((Unit) -> ProfileGeneralInfoViewViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileGeneralInfoViewStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate { ProfileInfoDelegate() }
        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        recyclers.add(binding.recycler)
        binding.toolbar.title.text = "Personal information"
        binding.toolbar.avatar bindClick {viewModel.avatarSelectClicked()}
        binding.editBtn bindClick { viewModel.onEditClicked() }
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
            addItemDecoration(ProfileGeneralInfoDecoration())

        }
    }

    private val singlePhotoPickerLauncher = registerForActivityResult(
        ProfileGeneralInfoEditAvatarImageSelectContract()
    ) { imageUri: Uri? ->
        imageUri?.let(viewModel::profileAvatarImageSelected)
    }


    override fun provideDiModule(): DI.Module =
        profileGeneralInfoViewDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileGeneralinfoViewBinding =
        ViewProfileGeneralinfoViewBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: ProfileGeneralInfoSideEffects) =
        when(sideEffect)
        {
            is ProfileGeneralInfoSideEffects.Error -> processErrorSideEffect(sideEffect.error)
            is ProfileGeneralInfoSideEffects.SelectAvatar -> {
                singlePhotoPickerLauncher.launch(null)
            }
        }

    override fun render(state: ProfileGeneralInfoViewState) {
        stateTransformer(state).run {
            this.data?.let {
                adapter.items = it
                if (binding.recycler.adapter == null) {
                    binding.recycler.swapAdapter(adapter, true)
                }
            }
            this.avatar?.let {avatarUrl->
                Glide
                    .with(binding.toolbar.avatar)
                    .load(avatarUrl)
                    .transform(
                        CircleCrop()
                    )
                    .dontAnimate()
                    .into(binding.toolbar.avatar)
            }
        }
    }

    override fun provideViewModel(): ProfileGeneralInfoViewViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfileGeneralInfoViewStateTransformer =
        stateTransformerFactory(Unit)

}

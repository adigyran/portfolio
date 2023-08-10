package com.aya.digital.core.feature.profile.security.changephone.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.argument
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.createFragment
import com.aya.digital.core.feature.profile.security.changephone.databinding.ViewProfileSecurityChangePhoneBinding
import com.aya.digital.core.feature.profile.security.changephone.di.profileSecurityChangePhoneDiModule
import com.aya.digital.core.feature.profile.security.changephone.ui.model.ProfileSecurityChangePhoneStateTransformer
import com.aya.digital.core.feature.profile.security.changephone.ui.model.ProfileSecurityChangePhoneUiModel
import com.aya.digital.core.feature.profile.security.changephone.viewmodel.ProfileSecurityChangePhoneSideEffects
import com.aya.digital.core.feature.profile.security.changephone.viewmodel.ProfileSecurityChangePhoneState
import com.aya.digital.core.feature.profile.security.changephone.viewmodel.ProfileSecurityChangePhoneViewModel
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.EmailDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.PhoneDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.PhoneFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.validated.ui.ValidatedFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.validated.ui.ValidatedFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.HeadlineTwoLineLabelDelegate
import kotlinx.parcelize.Parcelize
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

internal class ProfileSecurityChangePhoneView :
    DiFragment<ViewProfileSecurityChangePhoneBinding, ProfileSecurityChangePhoneViewModel, ProfileSecurityChangePhoneState, ProfileSecurityChangePhoneSideEffects, ProfileSecurityChangePhoneUiModel, ProfileSecurityChangePhoneStateTransformer>() {

    private var param: Param by argument("param")


    private val viewModelFactory: ((Unit) -> ProfileSecurityChangePhoneViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileSecurityChangePhoneStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate { HeadlineTwoLineLabelDelegate() }
            delegate { ValidatedFieldDelegate(ValidatedFieldDelegateListeners(viewModel::phoneChanged)) }
        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        binding.toolbar.backButton bindClick {viewModel.onBack()}
        binding.saveBtn bindClick {viewModel.saveClicked()}
        binding.toolbar.title.text = "Change phone"
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
            addItemDecoration(ProfileSecurityChangePhoneDecoration())
        }
    }


    override fun provideDiModule(): DI.Module =
        profileSecurityChangePhoneDiModule(tryTyGetParentRouter(), param)

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileSecurityChangePhoneBinding =
        ViewProfileSecurityChangePhoneBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: ProfileSecurityChangePhoneSideEffects) =
        when(sideEffect)
        {
            is ProfileSecurityChangePhoneSideEffects.Error -> processErrorSideEffect(sideEffect.error)
        }

    override fun render(state: ProfileSecurityChangePhoneState) {
        stateTransformer(state).data?.let {
            adapter.items = it
            if (binding.recycler.adapter == null) {
                binding.recycler.swapAdapter(adapter, true)
            }
        }
    }

    override fun provideViewModel(): ProfileSecurityChangePhoneViewModel =
        viewModelFactory(Unit)

    override fun provideStateTransformer(): ProfileSecurityChangePhoneStateTransformer =
        stateTransformerFactory(Unit)

    @Parcelize
    class Param(
        val requestCode: String,
        val phone:String
    ) : Parcelable

    companion object {
        fun getNewInstance(requestCode: String,phone: String): ProfileSecurityChangePhoneView =
            createFragment(
                Param(requestCode,phone)
            )
    }

}

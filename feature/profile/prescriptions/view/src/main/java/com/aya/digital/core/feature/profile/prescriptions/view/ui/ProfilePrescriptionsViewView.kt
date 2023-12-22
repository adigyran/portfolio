package com.aya.digital.core.feature.profile.prescriptions.view.ui

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.argument
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.createFragment
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.ext.gone
import com.aya.digital.core.ext.visible
import com.aya.digital.core.feature.profile.prescriptions.view.databinding.ViewProfilePrescriptionsViewBinding
import com.aya.digital.core.feature.profile.prescriptions.view.di.profilePrescriptionsViewDiModule
import com.aya.digital.core.navigation.contracts.imagepicker.ImageSelectContract
import com.aya.digital.core.feature.profile.prescriptions.view.ui.model.ProfilePrescriptionsViewStateTransformer
import com.aya.digital.core.feature.profile.prescriptions.view.ui.model.ProfilePrescriptionsViewUiModel
import com.aya.digital.core.feature.profile.prescriptions.view.viewmodel.ProfilePrescriptionsViewSideEffects
import com.aya.digital.core.feature.profile.prescriptions.view.viewmodel.ProfilePrescriptionsViewState
import com.aya.digital.core.feature.profile.prescriptions.view.viewmodel.ProfilePrescriptionsViewViewModel
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.delegates.components.fields.name.ui.NameFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.name.ui.NameFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.selection.ui.SelectionFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.selection.ui.SelectionFieldDelegateListeners
import com.aya.digital.core.ui.delegates.profile.insurance.ui.InsurancePolicyPhotoDelegate
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.parcelize.Parcelize
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

const val MAX_IMAGE_SIZE_KB = 2900

class ProfileInsuranceAddView :
    DiFragment<ViewProfilePrescriptionsViewBinding, ProfilePrescriptionsViewViewModel, ProfilePrescriptionsViewState, ProfilePrescriptionsViewSideEffects, ProfilePrescriptionsViewUiModel, ProfilePrescriptionsViewStateTransformer>() {

    private var param: Param by argument("param")

    private val viewModelFactory: ((Unit) -> ProfilePrescriptionsViewViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfilePrescriptionsViewStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate { NameFieldDelegate(NameFieldDelegateListeners({tag: Int, text: String ->  })) }
        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        recyclers.add(binding.recycler)
        binding.toolbar.backButton bindClick {viewModel.onBack()}
        binding.toolbar.title.text = "Insurance"
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
            addItemDecoration(ProfilePrescriptionsViewDecoration())
        }
    }



    override fun provideDiModule(): DI.Module =
        profilePrescriptionsViewDiModule(tryTyGetParentRouter(), param)

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfilePrescriptionsViewBinding =
        ViewProfilePrescriptionsViewBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: ProfilePrescriptionsViewSideEffects) =
        when(sideEffect)
        {
            is ProfilePrescriptionsViewSideEffects.Error -> processErrorSideEffect(sideEffect.error)
        }



    override fun render(state: ProfilePrescriptionsViewState) {
        stateTransformer(state).let {

        }
    }

    @Parcelize
    class Param(
        val requestCode: String,
        val insuranceId: Int?
    ) : Parcelable

    override fun provideViewModel(): ProfilePrescriptionsViewViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfilePrescriptionsViewStateTransformer =
        stateTransformerFactory(Unit)

    companion object {
        fun getNewInstance(requestCode: String, insuranceId: Int?): ProfileInsuranceAddView =
            createFragment(
                Param(requestCode, insuranceId)
            )
    }
}

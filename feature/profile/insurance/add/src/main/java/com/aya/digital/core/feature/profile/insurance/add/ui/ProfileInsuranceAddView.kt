package com.aya.digital.core.feature.profile.insurance.add.ui

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
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
import com.aya.digital.core.feature.profile.insurance.add.databinding.ViewProfileInsuranceAddBinding
import com.aya.digital.core.feature.profile.insurance.add.di.profileInsuranceAddDiModule
import com.aya.digital.core.feature.profile.insurance.add.ui.contract.ProfileInsuranceAddImageSelectContract
import com.aya.digital.core.feature.profile.insurance.add.ui.model.ProfileInsuranceAddStateTransformer
import com.aya.digital.core.feature.profile.insurance.add.ui.model.ProfileInsuranceAddUiModel
import com.aya.digital.core.feature.profile.insurance.add.viewmodel.ProfileInsuranceAddSideEffects
import com.aya.digital.core.feature.profile.insurance.add.viewmodel.ProfileInsuranceAddState
import com.aya.digital.core.feature.profile.insurance.add.viewmodel.ProfileInsuranceAddViewModel
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.delegates.components.fields.name.model.ui.NameFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.name.model.ui.NameFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.selection.ui.SelectionFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.selection.ui.SelectionFieldDelegateListeners
import com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.ui.InsurancePolicyPhotoDelegate
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.parcelize.Parcelize
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class ProfileInsuranceAddView :
    DiFragment<ViewProfileInsuranceAddBinding, ProfileInsuranceAddViewModel, ProfileInsuranceAddState, ProfileInsuranceAddSideEffects, ProfileInsuranceAddUiModel, ProfileInsuranceAddStateTransformer>() {

    private var param: Param by argument("param")

    private val viewModelFactory: ((Unit) -> ProfileInsuranceAddViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileInsuranceAddStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate { NameFieldDelegate(NameFieldDelegateListeners(viewModel::nameFieldChanged)) }
            delegate { SelectionFieldDelegate(SelectionFieldDelegateListeners(viewModel::onSelectionFieldClicked)) }
            delegate {
                InsurancePolicyPhotoDelegate(
                    viewModel::photoClicked,
                    viewModel::uploadPhotoClicked,
                    viewModel::photoMoreClicked
                )
            }
        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        recyclers.add(binding.recycler)
        binding.fullScreenPolicy.gone()
        binding.fullScreenPolicyBg bindClick { viewModel.onFullScreenPhotoClicked() }
        binding.toolbar.backButton bindClick {viewModel.onBack()}
        binding.toolbar.title.text = "Insurance"
        binding.saveAddButton bindClick {viewModel.onSaveAddClicked()}
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
            addItemDecoration(ProfileInsuranceAddDecoration())
        }
    }

    private val singlePhotoPickerLauncher = registerForActivityResult(ProfileInsuranceAddImageSelectContract()) { imageUri: Uri? ->
        imageUri?.let(viewModel::imageSelected)
    }

    override fun provideDiModule(): DI.Module =
        profileInsuranceAddDiModule(tryTyGetParentRouter(), param)

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileInsuranceAddBinding =
        ViewProfileInsuranceAddBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: ProfileInsuranceAddSideEffects) =
        when(sideEffect)
        {
            is ProfileInsuranceAddSideEffects.Error -> processErrorSideEffect(sideEffect.error)
            ProfileInsuranceAddSideEffects.ShowInsuranceActionsDialog -> showInsuranceActionsDialog()
            ProfileInsuranceAddSideEffects.SelectImage -> {
                singlePhotoPickerLauncher.launch(null)
            }

            ProfileInsuranceAddSideEffects.ShowFullScreenPolicy -> {
                binding.fullScreenPolicy.visible()
            }

            ProfileInsuranceAddSideEffects.HideFullScreenPolicy -> {
                binding.fullScreenPolicy.gone()
            }
        }


    private fun showInsuranceActionsDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete?")
            .setMessage("Are you sure you want to remove this insurance?")
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton("Delete") { dialog, which ->
                viewModel.deleteInsurance()
                dialog.dismiss()
            }
            .show()
    }
    override fun render(state: ProfileInsuranceAddState) {
        stateTransformer(state).let {
            it.data?.let {
                adapter.items = it
                if (binding.recycler.adapter == null) {
                    binding.recycler.swapAdapter(adapter, true)
                }
            }
            it.saveAddButtonText.run { binding.saveAddButton.text = this }
            it.fullScreenPolicyUrl?.let {photo->
                Glide
                    .with(binding.ivFullScreenPolicy)
                    .load(photo)
                    .transform(
                        CenterCrop(),
                        RoundedCorners(8.dpToPx())
                    )
                    .dontAnimate()
                    .into(binding.ivFullScreenPolicy)
            }
        }
    }

    @Parcelize
    class Param(
        val requestCode: String,
        val insuranceId: Int?
    ) : Parcelable

    override fun provideViewModel(): ProfileInsuranceAddViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfileInsuranceAddStateTransformer =
        stateTransformerFactory(Unit)

    companion object {
        fun getNewInstance(requestCode: String, insuranceId: Int?): ProfileInsuranceAddView =
            createFragment(
                Param(requestCode, insuranceId)
            )
    }
}

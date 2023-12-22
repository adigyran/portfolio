package com.aya.digital.core.feature.profile.prescriptions.edit.ui

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
import com.aya.digital.core.feature.profile.prescriptions.edit.databinding.ViewProfilePrescriptionsEditBinding
import com.aya.digital.core.feature.profile.prescriptions.edit.di.profilePrescriptionsEditDiModule
import com.aya.digital.core.navigation.contracts.imagepicker.ImageSelectContract
import com.aya.digital.core.feature.profile.prescriptions.edit.ui.model.ProfilePrescriptionsEditStateTransformer
import com.aya.digital.core.feature.profile.prescriptions.edit.ui.model.ProfilePrescriptionsEditUiModel
import com.aya.digital.core.feature.profile.prescriptions.edit.viewmodel.ProfilePrescriptionsEditSideEffects
import com.aya.digital.core.feature.profile.prescriptions.edit.viewmodel.ProfilePrescriptionsEditState
import com.aya.digital.core.feature.profile.prescriptions.edit.viewmodel.ProfilePrescriptionsEditViewModel
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
    DiFragment<ViewProfilePrescriptionsEditBinding, ProfilePrescriptionsEditViewModel, ProfilePrescriptionsEditState, ProfilePrescriptionsEditSideEffects, ProfilePrescriptionsEditUiModel, ProfilePrescriptionsEditStateTransformer>() {

    private var param: Param by argument("param")

    private val viewModelFactory: ((Unit) -> ProfilePrescriptionsEditViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfilePrescriptionsEditStateTransformer) by kodein.on(
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
            addItemDecoration(ProfilePrescriptionsEditDecoration())
        }
    }

    private val singlePhotoPickerLauncher = registerForActivityResult(
        ImageSelectContract()
    ) { imageUri: Uri? ->
        imageUri?.let {
            requireActivity().contentResolver.query(imageUri,null,null,null)?.use { cursor: Cursor ->
                val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                cursor.moveToFirst()
                val size = cursor.getLong(sizeIndex)
                if(size< MAX_IMAGE_SIZE_KB *1024) {
                    viewModel.imageSelected(it)
                } else showSizeLimitDialog()
            }
        }

    }

    private fun showSizeLimitDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Error")
            .setMessage("Image too big, choose other image")
            .setPositiveButton("Ok") { dialog, which ->
                dialog.dismiss()
            }
            .show()

    }

    override fun provideDiModule(): DI.Module =
        profilePrescriptionsEditDiModule(tryTyGetParentRouter(), param)

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfilePrescriptionsEditBinding =
        ViewProfilePrescriptionsEditBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: ProfilePrescriptionsEditSideEffects) =
        when(sideEffect)
        {
            is ProfilePrescriptionsEditSideEffects.Error -> processErrorSideEffect(sideEffect.error)
            ProfilePrescriptionsEditSideEffects.ShowInsuranceActionsDialog -> showInsuranceActionsDialog()
            ProfilePrescriptionsEditSideEffects.SelectImage -> {
                singlePhotoPickerLauncher.launch(null)
            }

            ProfilePrescriptionsEditSideEffects.ShowFullScreenPolicy -> {
                binding.fullScreenPolicy.visible()
            }

            ProfilePrescriptionsEditSideEffects.HideFullScreenPolicy -> {
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
    override fun render(state: ProfilePrescriptionsEditState) {
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

    override fun provideViewModel(): ProfilePrescriptionsEditViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfilePrescriptionsEditStateTransformer =
        stateTransformerFactory(Unit)

    companion object {
        fun getNewInstance(requestCode: String, insuranceId: Int?): ProfileInsuranceAddView =
            createFragment(
                Param(requestCode, insuranceId)
            )
    }
}

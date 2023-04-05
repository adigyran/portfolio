package com.aya.digital.core.feature.profile.generalinfo.edit.ui

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.domain.profile.generalinfo.edit.model.ProfileEditModel
import com.aya.digital.core.domain.profile.generalinfo.view.model.ProfileInfoModel
import com.aya.digital.core.ext.argument
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.createFragment
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.feature.profile.generalinfo.edit.databinding.ViewProfileGeneralinfoEditBinding
import com.aya.digital.core.feature.profile.generalinfo.edit.di.profileGeneralInfoEditDiModule
import com.aya.digital.core.feature.profile.generalinfo.edit.ui.model.ProfileGeneralInfoEditStateTransformer
import com.aya.digital.core.feature.profile.generalinfo.edit.ui.model.ProfileGeneralInfoEditUiModel
import com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel.ProfileGeneralInfoEditSideEffect
import com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel.ProfileGeneralInfoEditState
import com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel.ProfileGeneralInfoEditViewModel
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.delegates.components.fields.dropdown.ui.DropDownFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.dropdown.ui.DropDownFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.selection.ui.SelectionFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.name.model.ui.*
import com.aya.digital.core.ui.delegates.components.fields.selection.ui.SelectionFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.validated.ui.ValidatedFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.validated.ui.ValidatedFieldDelegateListeners
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.parcelize.Parcelize
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

class ProfileGeneralInfoEditView :
    DiFragment<ViewProfileGeneralinfoEditBinding, ProfileGeneralInfoEditViewModel, ProfileGeneralInfoEditState, ProfileGeneralInfoEditSideEffect, ProfileGeneralInfoEditUiModel, ProfileGeneralInfoEditStateTransformer>() {

    private var param: Param by argument("param")

    private val viewModelFactory: ((Unit) -> ProfileGeneralInfoEditViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileGeneralInfoEditStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate { NameFieldDelegate(NameFieldDelegateListeners(viewModel::nameFieldChanged)) }
            delegate { ValidatedNumberFieldDelegate(ValidatedNumberFieldDelegateListeners(viewModel::numberFieldChanged)) }
            delegate { ValidatedFieldDelegate(ValidatedFieldDelegateListeners(viewModel::numberFieldChanged)) }
            delegate { SelectionFieldDelegate(SelectionFieldDelegateListeners(viewModel::selectFieldClicked)) }
            delegate {
                DropDownFieldDelegate(DropDownFieldDelegateListeners { tag, selectedItem ->
                    viewModel.dropDownSelected(
                        tag,
                        selectedItem.tag
                    )
                })
            }
        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        binding.saveBtn bindClick { viewModel.onSaveProfileClicked() }
        binding.toolbar.title.text = "Edit personal information"
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
            addItemDecoration(ProfileGeneralInfoEditDecoration())
        }
    }


    override fun sideEffect(sideEffect: ProfileGeneralInfoEditSideEffect) =
        when (sideEffect) {

            is ProfileGeneralInfoEditSideEffect.ShowBirthdayDatePicker -> {
                showBirthdayDatePicker(sideEffect.selectedDate)
            }
            is ProfileGeneralInfoEditSideEffect.Error -> processErrorSideEffect(sideEffect.error)
        }

    private fun showBirthdayDatePicker(selectedDate: LocalDate?) {
        val materialDatePicker = MaterialDatePicker.Builder.datePicker()
            .build()
        materialDatePicker
            .addOnPositiveButtonClickListener {
                val ofEpochMilli = Instant.ofEpochMilli(it)
                val birthDay =
                    LocalDateTime.ofInstant(ofEpochMilli, ZoneId.of("UTC")).toLocalDate()
                viewModel.birthDaySelected(birthDay)
            }
        materialDatePicker.show(childFragmentManager, "BirthDAY")
    }


    override fun provideDiModule(): DI.Module =
        profileGeneralInfoEditDiModule(tryTyGetParentRouter(), param)

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileGeneralinfoEditBinding =
        ViewProfileGeneralinfoEditBinding.inflate(inflater, container, false)

    override fun render(state: ProfileGeneralInfoEditState) {
        stateTransformer(state).run {
            this.data?.let {
                adapter.items = it
                if (binding.recycler.adapter == null) {
                    binding.recycler.swapAdapter(adapter, true)
                }
            }
        }
    }

    override fun provideViewModel(): ProfileGeneralInfoEditViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfileGeneralInfoEditStateTransformer =
        stateTransformerFactory(Unit)

    @Parcelize
    class Param(
        val requestCode: String,
        val profileModel: ProfileInfoModel?
    ) : Parcelable

    companion object {
        fun getNewInstance(
            requestCode: String,
            profileModel: ProfileInfoModel?
        ): ProfileGeneralInfoEditView = createFragment(
            Param(requestCode, profileModel)
        )
    }
}

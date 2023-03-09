package com.aya.digital.core.feature.profile.generalinfo.edit.ui

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
import com.aya.digital.core.feature.profile.generalinfo.edit.databinding.ViewProfileGeneralinfoEditBinding
import com.aya.digital.core.feature.profile.generalinfo.edit.di.profileGeneralInfoEditDiModule
import com.aya.digital.core.feature.profile.generalinfo.edit.ui.model.ProfileGeneralInfoEditStateTransformer
import com.aya.digital.core.feature.profile.generalinfo.edit.ui.model.ProfileGeneralInfoEditUiModel
import com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel.ProfileGeneralInfoEditSideEffect
import com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel.ProfileGeneralInfoEditState
import com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel.ProfileGeneralInfoEditViewModel
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.delegates.components.fields.name.model.ui.NameFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.name.model.ui.ValidatedNumberFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.name.model.ui.nameFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.name.model.ui.validatedNumberFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.dropdown.ui.DropDownFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.selection.ui.SelectionFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.dropdown.ui.dropDownFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.selection.ui.selectionFieldDelegate
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
            delegate { nameFieldDelegate(NameFieldDelegateListeners(viewModel::nameFieldChanged)) }
            delegate { validatedNumberFieldDelegate(ValidatedNumberFieldDelegateListeners(viewModel::numberFieldChanged)) }
            delegate { selectionFieldDelegate(SelectionFieldDelegateListeners(viewModel::selectFieldClicked)) }
            delegate {
                dropDownFieldDelegate(DropDownFieldDelegateListeners { tag, selectedItem ->
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

    override fun sideEffect(sideEffect: ProfileGeneralInfoEditSideEffect) =
        when (sideEffect) {

            is ProfileGeneralInfoEditSideEffect.ShowBirthdayDatePicker -> {
                showBirthdayDatePicker(sideEffect.selectedDate)
            }
            else -> {}
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
        stateTransformer(state).data?.let {
            adapter.items = it
            if (binding.recycler.adapter == null) {
                binding.recycler.swapAdapter(adapter, true)
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

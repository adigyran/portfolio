package com.aya.digital.feature.bottomdialogs.createappointmentdialog.ui

import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.argument
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.createFragment
import com.aya.digital.core.ext.toggleVisibility
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.di.createAppointmentDialogDiModule
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.viewmodel.CreateAppointmentDialogState
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.viewmodel.CreateAppointmentDialogViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiBottomSheetDialogFragment
import com.aya.digital.core.ui.delegates.components.fields.name.ui.NameFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.name.ui.NameFieldDelegateListeners
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.ui.DoctorDateTitleDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.ui.DoctorSlotDelegate
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.databinding.ViewCreateAppointmentDialogBinding
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.ui.model.CreateAppointmentDialogStateTransformer
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.ui.model.CreateAppointmentDialogUiModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on
import timber.log.Timber

class CreateAppointmentDialogView :
    DiBottomSheetDialogFragment<ViewCreateAppointmentDialogBinding, CreateAppointmentDialogViewModel, CreateAppointmentDialogState, BaseSideEffect, CreateAppointmentDialogUiModel, CreateAppointmentDialogStateTransformer>() {

    private var param: Param by argument("param")

    private var initialised: Boolean = false

    private val viewModelFactory: ((Unit) -> CreateAppointmentDialogViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> CreateAppointmentDialogStateTransformer) by kodein.on(
        context = this
    ).factory()

    override fun provideViewModel(): CreateAppointmentDialogViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): CreateAppointmentDialogStateTransformer =
        stateTransformerFactory(Unit)


    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate { DoctorSlotDelegate(viewModel::onSlotClicked) }
            delegate { NameFieldDelegate(NameFieldDelegateListeners(viewModel::onNameFieldChanged)) }
            delegate { DoctorDateTitleDelegate() }
        }
    }

    private lateinit var lm: GridLayoutManager

    override fun provideDiModule(): DI.Module =
        createAppointmentDialogDiModule(tryTyGetParentRouter(), param)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
        Timber.d("AAAA")
        binding.btnClose bindClick { viewModel.close() }
        binding.btnBack.bindClick { viewModel.close() }
        binding.btnBook.bindClick { viewModel.onBookClicked() }
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetBehavior.skipCollapsed = true
        with(binding.recycler) {
            itemAnimator = null
            lm = GridLayoutManager(
                context,
                4,
                RecyclerView.VERTICAL,
                false
            )


            layoutManager = lm
            addItemDecoration(CreateAppointmentDecoration())
        }
    }

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewCreateAppointmentDialogBinding =
        ViewCreateAppointmentDialogBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: CreateAppointmentDialogState) {
        stateTransformer(state).run {
            dateText?.let { date ->
                binding.titleTv.text = date
            }
            data?.let {
                adapter.items = it
                if (binding.recycler.adapter == null) {
                    binding.recycler.swapAdapter(adapter, true)
                    lm.spanSizeLookup = CreateAppointmentSpanSizeLookup(adapter)
                }
            }
            isTelemed?.let {
                binding.grTelemed.toggleVisibility(it)
            }
        }
    }


    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }

    @Parcelize
    class Param(
        val requestCode: String,
        val doctorId: Int,
        val slotDateTime: java.time.LocalDateTime?,
        val date: @RawValue java.time.LocalDate?
    ) : Parcelable

    companion object {
        fun getNewInstance(
            requestCode: String,
            doctorId: Int,
            slotDateTime: LocalDateTime?,
            date: LocalDate?
        ): CreateAppointmentDialogView =
            createFragment(
                Param(
                    requestCode,
                    doctorId = doctorId,
                    slotDateTime = slotDateTime?.toJavaLocalDateTime(),
                    date = date?.toJavaLocalDate()
                )
            )
    }
}

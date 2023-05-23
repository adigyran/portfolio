package com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.ui

import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.argument
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.createFragment
import com.aya.digital.core.ext.toggleVisibility
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.di.dateAppointmentsDialogDiModule
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.viewmodel.DateAppointmentsDialogState
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.viewmodel.DateAppointmentsDialogViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiBottomSheetDialogFragment
import com.aya.digital.core.ui.delegates.appointments.patientappointment.ui.PatientAppointmentDelegate
import com.aya.digital.core.ui.delegates.appointments.patientappointment.ui.PatientEmptyAppointmentDelegate
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.databinding.ViewDateAppointmentsDialogBinding
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.ui.model.DateAppointmentsDialogStateTransformer
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.ui.model.DateAppointmentsDialogDialogUiModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class DateAppointmentsDialogView :
    DiBottomSheetDialogFragment<ViewDateAppointmentsDialogBinding, DateAppointmentsDialogViewModel, DateAppointmentsDialogState, BaseSideEffect, DateAppointmentsDialogDialogUiModel, DateAppointmentsDialogStateTransformer>() {

    private var param: Param by argument("param")

    private var initialised: Boolean = false

    private val viewModelFactory: ((Unit) -> DateAppointmentsDialogViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> DateAppointmentsDialogStateTransformer) by kodein.on(
        context = this
    ).factory()

    override fun provideViewModel(): DateAppointmentsDialogViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): DateAppointmentsDialogStateTransformer =
        stateTransformerFactory(Unit)


    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate { PatientAppointmentDelegate(viewModel::onAppointmentClicked) }
            delegate { PatientEmptyAppointmentDelegate() }
        }
    }

    private lateinit var lm: GridLayoutManager

    override fun provideDiModule(): DI.Module =
        dateAppointmentsDialogDiModule(tryTyGetParentRouter(), param)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
/*        binding.btnClose bindClick { viewModel.close() }
        binding.btnBack.bindClick { viewModel.close() }*/
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetBehavior.skipCollapsed = true
        with(binding.recycler) {
            itemAnimator = null

            val lm = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
            layoutManager = lm
            addItemDecoration(DateAppointmentsDialogDecoration())
        }
    }

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDateAppointmentsDialogBinding =
        ViewDateAppointmentsDialogBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: DateAppointmentsDialogState) {
        stateTransformer(state).run {
            data?.let {
                adapter.items = it
                if (binding.recycler.adapter == null) {
                    binding.recycler.swapAdapter(adapter, true)
                }
            }
        }
    }


    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }

    @Parcelize
    class Param(
        val requestCode: String,
        val date: @RawValue LocalDate
    ) : Parcelable

    companion object {
        fun getNewInstance(
            requestCode: String,
            date: LocalDate
        ): DateAppointmentsDialogView =
            createFragment(
                Param(
                    requestCode = requestCode,
                    date = date
                )
            )
    }
}

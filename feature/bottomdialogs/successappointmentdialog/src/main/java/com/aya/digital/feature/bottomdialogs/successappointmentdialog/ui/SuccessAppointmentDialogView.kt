package com.aya.digital.feature.bottomdialogs.successappointmentdialog.ui

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
import com.aya.digital.feature.bottomdialogs.successappointmentdialog.di.successAppointmentDialogDiModule
import com.aya.digital.feature.bottomdialogs.successappointmentdialog.viewmodel.SuccessAppointmentDialogState
import com.aya.digital.feature.bottomdialogs.successappointmentdialog.viewmodel.SuccessAppointmentDialogViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiBottomSheetDialogFragment
import com.aya.digital.feature.bottomdialogs.successappointmentdialog.databinding.ViewSuccessAppointmentDialogBinding
import com.aya.digital.feature.bottomdialogs.successappointmentdialog.ui.model.SuccessAppointmentDialogStateTransformer
import com.aya.digital.feature.bottomdialogs.successappointmentdialog.ui.model.SuccessAppointmentDialogUiModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on
import timber.log.Timber

class SuccessAppointmentDialogView :
    DiBottomSheetDialogFragment<ViewSuccessAppointmentDialogBinding, SuccessAppointmentDialogViewModel, SuccessAppointmentDialogState, BaseSideEffect, SuccessAppointmentDialogUiModel, SuccessAppointmentDialogStateTransformer>() {

    private var param: Param by argument("param")

    private var initialised: Boolean = false

    private val viewModelFactory: ((Unit) -> SuccessAppointmentDialogViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> SuccessAppointmentDialogStateTransformer) by kodein.on(
        context = this
    ).factory()

    override fun provideViewModel(): SuccessAppointmentDialogViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): SuccessAppointmentDialogStateTransformer =
        stateTransformerFactory(Unit)


    override fun provideDiModule(): DI.Module =
        successAppointmentDialogDiModule(tryTyGetParentRouter(), param)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
        Timber.d("AAAA")
        binding.btnClose bindClick { viewModel.close() }
    }

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewSuccessAppointmentDialogBinding =
        ViewSuccessAppointmentDialogBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: SuccessAppointmentDialogState) {
        stateTransformer(state).run {
            successText?.let {
                binding.tvInfo.text = it
            }
        }
    }


    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }

    @Parcelize
    class Param(
        val requestCode: String,
        val appointmentId: Int
    ) : Parcelable

    companion object {
        fun getNewInstance(
            requestCode: String,
            appointmentId: Int
        ): SuccessAppointmentDialogView =
            createFragment(
                Param(
                    requestCode,
                    appointmentId = appointmentId
                )
            )
    }
}

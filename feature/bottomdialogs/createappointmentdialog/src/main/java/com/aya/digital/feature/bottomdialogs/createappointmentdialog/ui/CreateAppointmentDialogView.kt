package com.aya.digital.feature.bottomdialogs.createappointmentdialog.ui

import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.aya.digital.core.domain.schedule.base.model.ScheduleSlotModel
import com.aya.digital.core.ext.argument
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.createFragment
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.di.createAppointmentDialogDiModule
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.viewmodel.CreateAppointmentDialogState
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.viewmodel.CreateAppointmentDialogViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.base.screens.DiBottomSheetDialogFragment
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.databinding.ViewCreateAppointmentDialogBinding
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.ui.model.CreateAppointmentDialogStateTransformer
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.ui.model.CreateAppointmentDialogUiModel
import com.mukesh.OTP_VIEW_TYPE_BORDER
import com.mukesh.OtpView
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on
import kotlin.properties.Delegates

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


    override fun provideDiModule(): DI.Module = createAppointmentDialogDiModule(tryTyGetParentRouter(), param)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
       // binding.otpView.setOtpCompletionListener(viewModel::codeChanged)
        binding.btnClose bindClick {viewModel.close()}

    }

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewCreateAppointmentDialogBinding = ViewCreateAppointmentDialogBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: CreateAppointmentDialogState) {
        val uiModel = stateTransformer(state)
        if (!initialised) {
            initialised = true

        }
    }


    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }

    @Parcelize
    class Param(
        val requestCode: String,
        val slotDateTime:@RawValue LocalDateTime?,
        val date:@RawValue LocalDate?
    ) : Parcelable

    companion object {
        fun getNewInstance(requestCode: String, slotDateTime: LocalDateTime?, date:LocalDate?): CreateAppointmentDialogView =
            createFragment(Param(requestCode, slotDateTime = slotDateTime, date = date))
    }
}

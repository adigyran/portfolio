package com.aya.digital.feature.bottomdialogs.successappointmentdialog.ui.model

import android.content.Context
import android.text.format.DateUtils
import com.aya.digital.core.ext.strings
import com.aya.digital.core.localisation.R
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.util.datetime.DateTimeUtils
import com.aya.digital.feature.bottomdialogs.successappointmentdialog.FieldsTags

import com.aya.digital.feature.bottomdialogs.successappointmentdialog.viewmodel.SuccessAppointmentDialogState
import kotlinx.datetime.*

class SuccessAppointmentDialogStateTransformer(
    private val context: Context,
    private val dateTimeUtils: DateTimeUtils
) : BaseStateTransformer<SuccessAppointmentDialogState, SuccessAppointmentDialogUiModel>() {
    override fun invoke(state: SuccessAppointmentDialogState): SuccessAppointmentDialogUiModel =
        SuccessAppointmentDialogUiModel(
            successText = kotlin.run {
                context.strings[R.string.appointment_success_dialog_doctor].format(
                    state.doctorName,
                    state.dateTime.getAppointmentDateTimeText()
                )
            }
        )

    private fun LocalDateTime?.getAppointmentDateTimeText() =
        this?.let { dateTimeUtils.formatAppointmentCardDateTime(it) } ?: ""

}
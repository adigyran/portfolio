package com.aya.digital.feature.bottomdialogs.createappointmentdialog.ui.model

import android.content.Context
import android.text.format.DateUtils
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.util.datetime.DateTimeUtils

import com.aya.digital.feature.bottomdialogs.createappointmentdialog.viewmodel.CreateAppointmentDialogState
import kotlinx.datetime.*

class CreateAppointmentDialogStateTransformer(
    private val context: Context,
    private val dateUtils: DateTimeUtils
) : BaseStateTransformer<CreateAppointmentDialogState, CreateAppointmentDialogUiModel>() {
    override fun invoke(state: CreateAppointmentDialogState): CreateAppointmentDialogUiModel =
        CreateAppointmentDialogUiModel(
            dateText = state.date?.let { date ->
                val dateTime = date.atStartOfDayIn(TimeZone.currentSystemDefault())
                    .toLocalDateTime(TimeZone.currentSystemDefault())
                "%s, %s".format(
                    dateTime.getRelativeText(),
                    dateUtils.formatSlotTitleDate(dateTime)
                )
            } ?: ""
        )

    private fun LocalDateTime.getRelativeText() = DateUtils.getRelativeTimeSpanString(
        this.toInstant(
            TimeZone.currentSystemDefault()
        ).toEpochMilliseconds(),
        System.currentTimeMillis(),
        DateUtils.DAY_IN_MILLIS,
        DateUtils.FORMAT_ABBREV_RELATIVE
    );
}
package com.aya.digital.feature.bottomdialogs.createappointmentdialog.ui.model

import android.content.Context
import android.text.format.DateUtils
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.model.DoctorSlotUIModel
import com.aya.digital.core.util.datetime.DateTimeUtils

import com.aya.digital.feature.bottomdialogs.createappointmentdialog.viewmodel.CreateAppointmentDialogState
import kotlinx.datetime.*

class CreateAppointmentDialogStateTransformer(
    private val context: Context,
    private val dateTimeUtils: DateTimeUtils
) : BaseStateTransformer<CreateAppointmentDialogState, CreateAppointmentDialogUiModel>() {
    override fun invoke(state: CreateAppointmentDialogState): CreateAppointmentDialogUiModel =
        CreateAppointmentDialogUiModel(
            dateText = state.date?.let { date ->
                val dateTime = date.atStartOfDayIn(TimeZone.currentSystemDefault())
                    .toLocalDateTime(TimeZone.currentSystemDefault())
                "%s, %s".format(
                    dateTime.getRelativeText(),
                    dateTimeUtils.formatSlotTitleDate(dateTime)
                )
            } ?: "",
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    state.slots?.let { slots ->
                        addAll(slots
                            .map {
                                DoctorSlotUIModel(
                                    it.id,
                                    dateTimeUtils.formatSlotTime(it.startDate.time)
                                )
                            })
                    }
                }
            }
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
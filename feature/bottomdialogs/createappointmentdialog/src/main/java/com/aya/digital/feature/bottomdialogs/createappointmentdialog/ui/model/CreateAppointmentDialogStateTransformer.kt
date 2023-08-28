package com.aya.digital.feature.bottomdialogs.createappointmentdialog.ui.model

import android.content.Context
import android.text.format.DateUtils
import com.aya.digital.core.domain.schedule.base.model.SlotModelType
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.components.fields.name.model.NameFieldUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.model.DoctorDateTitleUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.model.DoctorSlotUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.ui.DoctorDateTitleDelegate
import com.aya.digital.core.util.datetime.DateTimeUtils
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.FieldsTags

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
                                    id = it.id,
                                    timeText = dateTimeUtils.formatSlotTime(it.startDate.time),
                                    selected = state.selectedSlotId?.let { selectedSlotId -> selectedSlotId == it.id }
                                        ?: false
                                )
                            })
                    }
                    if (state.selectedSlotId != null) {
                        add(DoctorDateTitleUIModel(0, "Write the purpose of the record"))
                        add(
                            NameFieldUIModel(
                                tag = FieldsTags.COMMENT_FIELD,
                                label = "Write a comment",
                                text = state.comment,
                                error = null
                            )
                        )
                    }
                }
            },
            isTelemed = state.slots?.let { slots ->
                val firstSlot = slots.first()
                return@let firstSlot.type is SlotModelType.Online
            } ?: false
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
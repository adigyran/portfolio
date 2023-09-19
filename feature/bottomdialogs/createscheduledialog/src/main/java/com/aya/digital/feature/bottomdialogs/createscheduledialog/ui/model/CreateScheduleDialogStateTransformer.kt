package com.aya.digital.feature.bottomdialogs.createscheduledialog.ui.model

import android.content.Context
import com.aya.digital.core.model.ProfileSex
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.appointments.appointmentsscheduler.createschedule.telemed.model.SchedulerCreateScheduleIsTelemedUIModel
import com.aya.digital.core.ui.delegates.components.fields.dropdown.model.AutoCompleteItem
import com.aya.digital.core.ui.delegates.components.fields.dropdown.model.DropdownFieldUIModel
import com.aya.digital.core.ui.delegates.components.fields.selection.model.SelectionFieldUIModel

import com.aya.digital.core.util.datetime.DateTimeUtils
import com.aya.digital.feature.bottomdialogs.createscheduledialog.FieldsTags

import com.aya.digital.feature.bottomdialogs.createscheduledialog.viewmodel.CreateScheduleDialogState
import com.aya.digital.feature.bottomdialogs.createscheduledialog.viewmodel.ScheduleSlotDuration
import kotlinx.datetime.toKotlinLocalTime
import java.time.LocalDateTime

class CreateScheduleDialogStateTransformer(
    private val context: Context,
    private val dateTimeUtils: DateTimeUtils
) : BaseStateTransformer<CreateScheduleDialogState, CreateScheduleDialogUiModel>() {
    override fun invoke(state: CreateScheduleDialogState): CreateScheduleDialogUiModel =
        CreateScheduleDialogUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    add(
                        SelectionFieldUIModel(
                            tag = FieldsTags.START_TIME_FIELD,
                            label = "Start time",
                            text = state.startTime?.formatTime() ?: "",
                            error = state.startTimeError
                        )
                    )
                    add(
                        SelectionFieldUIModel(
                            tag = FieldsTags.END_TIME_FIELD,
                            label = "End time",
                            text = state.endTime?.formatTime() ?: "",
                            error = state.endTimeError
                        )
                    )
                    state.availableDurations?.let { availableDurations ->
                        add(
                            DropdownFieldUIModel(
                                FieldsTags.SLOT_INTERVAL_FIELD,
                                availableDurations.getDurationList(),
                                "Consultation time",
                                state.selectedDuration?.durationMinutes?.let { "%d min".format(it) }
                                    ?: "",
                                null
                            )
                        )
                    }
                    add(
                        SchedulerCreateScheduleIsTelemedUIModel(
                            isTelemed = state.isTelemed
                        )
                    )

                }
            },
            createEnabled = kotlin.run { state.startTime!=null && state.endTime!=null && state.selectedDuration!=null }
        )

    private fun Set<ScheduleSlotDuration>.getDurationList() =
        map { scheduleDuration ->
            AutoCompleteItem(
                scheduleDuration.tag,
                "%d min".format(scheduleDuration.durationMinutes)
            )
        }

    private fun LocalDateTime.formatTime() =
        dateTimeUtils.formatSchedulerSlotTime(this.toLocalTime().toKotlinLocalTime())


}
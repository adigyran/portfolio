package com.aya.digital.feature.bottomdialogs.createscheduledialog.viewmodel

import com.aya.digital.core.domain.base.models.appointment.AppointmentModel
import com.aya.digital.core.domain.schedule.base.GetLatestScheduleByDoctorIdByDateUseCase
import com.aya.digital.core.domain.schedule.doctor.scheduler.CreateDaySlotsScheduleUseCase
import com.aya.digital.core.model.ProfileSex
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.bottomdialogs.createscheduledialog.navigation.CreateScheduleDialogNavigationEvents
import com.aya.digital.feature.bottomdialogs.createscheduledialog.ui.CreateScheduleDialogView
import com.aya.digital.feature.bottomdialogs.createscheduledialog.FieldsTags
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime

class CreateScheduleDialogViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val param: CreateScheduleDialogView.Param,
    private val createDaySlotsScheduleUseCase: CreateDaySlotsScheduleUseCase
) :
    BaseViewModel<CreateScheduleDialogState, CreateScheduleDialogSideEffects>() {
    override val container = container<CreateScheduleDialogState, CreateScheduleDialogSideEffects>(
        initialState = CreateScheduleDialogState(
            date = param.date ?: LocalDate.now()
        ),
    )
    {

    }

    init {

    }

    fun onSelectFieldClicked(tag: Int) = intent {
        val startTime = state.startTime?.toLocalTime()
        val endTime = state.endTime?.toLocalTime()
        val selectedTime = when (tag) {
            FieldsTags.START_TIME_FIELD -> startTime
            FieldsTags.END_TIME_FIELD -> endTime
            else -> null
        }
        postSideEffect(
            CreateScheduleDialogSideEffects.ShowTimePicker(
                tag = tag,
                date = state.date,
                selectedTime = selectedTime
            )
        )
    }

    fun dropDownSelected(tag: Int, selectedItemTag: String) = intent {
        when (tag) {
            FieldsTags.SLOT_INTERVAL_FIELD -> {
                val selectedDuration =
                    state.availableDurations?.firstOrNull { it.tag == selectedItemTag }
                selectedDuration?.let {
                    reduce { state.copy(selectedDuration = selectedDuration) }
                }
            }
        }
    }

    fun onTimePicked(tag: Int, dateTime: LocalDateTime) = intent {
        Timber.d("$tag $dateTime")
        selectTime(tag = tag, dateTime = dateTime)
    }

    private fun selectTime(tag: Int, dateTime: LocalDateTime) = intent {
        when (tag) {
            FieldsTags.START_TIME_FIELD -> {
                reduce {
                    state.copy(
                        availableDurations = null,
                        selectedDuration = null,
                        endTime = null,
                        startTime = dateTime
                    )
                }
            }

            FieldsTags.END_TIME_FIELD -> calculateEndTime(dateTime)

        }
    }

    private fun calculateEndTime(
        dateTime: LocalDateTime
    ) = intent {
        val startTime = state.startTime
        startTime?.let {
            val isValid = dateTime.isAfter(startTime)
            val endDateTime = when {
                isValid -> dateTime
                else -> null
            }
            reduce {
                state.copy(
                    availableDurations = null,
                    selectedDuration = null,
                    endTime = endDateTime,
                    endTimeError = endDateTime?.let { null } ?: "Can't be after start time"
                )
            }
            endDateTime?.let { calculateSlotsDurations() }
        }

    }

    private fun calculateSlotsDurations() = intent {
        val startTime = state.startTime
        val endTime = state.endTime
        if (startTime == null || endTime == null) return@intent
        val maxDurationMinutes = Duration.between(startTime, endTime).toMinutes()
        Timber.d("$maxDurationMinutes")
        val minDurationMinutes = 5
        var currentDurationMinutes = minDurationMinutes
        val durations = mutableSetOf<ScheduleSlotDuration>()
        while (currentDurationMinutes < maxDurationMinutes) {
            durations.add(
                ScheduleSlotDuration(
                    "%d".format(currentDurationMinutes),
                    currentDurationMinutes
                )
            )
            currentDurationMinutes += if (currentDurationMinutes < 30) {
                5
            } else currentDurationMinutes
        }
        reduce {
            state.copy(
                availableDurations = durations
            )
        }
    }


    fun onTelemedChecked(checked: Boolean) = intent {
        reduce { state.copy(isTelemed = !state.isTelemed) }
    }

    fun onCreateScheduleClicked() = intent {
        createSchedule()
    }

    private fun createSchedule() = intent {
        if (state.startTime == null || state.endTime == null || state.selectedDuration == null) return@intent
        createDaySlotsScheduleUseCase(
            state.startTime!!,
            state.endTime!!,
            state.selectedDuration!!.durationMinutes,
            state.isTelemed
        )
            .await()
            .processResult({
                if (it) coordinatorRouter.sendEvent(
                    CreateScheduleDialogNavigationEvents.FinishWithResult(
                        param.requestCode,
                        true
                    )
                )
            }, { processError(it) })
    }

    fun close() = intent {
        coordinatorRouter.sendEvent(CreateScheduleDialogNavigationEvents.Exit)
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }
}


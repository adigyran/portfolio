package com.aya.digital.feature.bottomdialogs.createappointmentdialog.viewmodel

import com.aya.digital.core.data.base.result.models.code.CodeResultModel
import com.aya.digital.core.domain.schedule.base.GetLatestScheduleByDoctorIdByDateUseCase
import com.aya.digital.core.domain.schedule.base.GetLatestScheduleByDoctorIdUseCase
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.navigation.CreateAppointmentDialogNavigationEvents
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.ui.CreateAppointmentDialogView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.reactive.asFlow
import kotlinx.datetime.LocalDate
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CreateAppointmentDialogViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val param: CreateAppointmentDialogView.Param,
    private val getLatestScheduleByDoctorIdByDateUseCase: GetLatestScheduleByDoctorIdByDateUseCase

) :
    BaseViewModel<CreateAppointmentDialogState, BaseSideEffect>() {
    override val container = container<CreateAppointmentDialogState, BaseSideEffect>(
        initialState = CreateAppointmentDialogState(
        ),
    )
    {

    }

    init {
        loadDaySlots()
    }

    private fun loadDaySlots() = intent(registerIdling = false) {
        param.date?.let { date ->
            getLatestScheduleByDoctorIdByDateUseCase(param.doctorId, date)
                .asFlow()
                .collect { resultModel ->
                    resultModel.processResult({slots->
                     reduce { state.copy(
                         slots = slots,
                         date = date
                     ) }
                    }, { processError(it) })
                }
        }
    }

    fun close() = intent {
        coordinatorRouter.sendEvent(CreateAppointmentDialogNavigationEvents.Exit)
    }


    fun onSlotClicked(slotId: Int, date: LocalDate?) = intent {

    }


    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) {
    }
}


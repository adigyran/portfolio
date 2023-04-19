package com.aya.digital.feature.bottomdialogs.createappointmentdialog.ui.model

import android.content.Context
import com.aya.digital.core.mvi.BaseStateTransformer

import com.aya.digital.feature.bottomdialogs.createappointmentdialog.viewmodel.CreateAppointmentDialogState

class CreateAppointmentDialogStateTransformer(context : Context): BaseStateTransformer<CreateAppointmentDialogState, CreateAppointmentDialogUiModel>() {
    override fun invoke(state: CreateAppointmentDialogState): CreateAppointmentDialogUiModel =
        CreateAppointmentDialogUiModel(

        )


}
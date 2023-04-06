package com.aya.digital.core.feature.tabviews.appointments.ui.model

import android.content.Context
import com.aya.digital.core.feature.tabviews.appointments.viewmodel.AppointmentsState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem

class AppointmentsStateTransformer(context : Context): BaseStateTransformer<AppointmentsState, AppointmentsUiModel>() {
    override fun invoke(state: AppointmentsState): AppointmentsUiModel =
        AppointmentsUiModel(
            data = kotlin.run {
               return@run mutableListOf<DiffItem>().apply {

                }
            }
        )


}
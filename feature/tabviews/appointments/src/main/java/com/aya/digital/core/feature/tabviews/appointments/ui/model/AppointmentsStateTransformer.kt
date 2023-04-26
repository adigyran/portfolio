package com.aya.digital.core.feature.tabviews.appointments.ui.model

import android.content.Context
import com.aya.digital.core.feature.tabviews.appointments.viewmodel.AppointmentsState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.PatientAppointmentUIModel
import timber.log.Timber

class AppointmentsStateTransformer(context : Context): BaseStateTransformer<AppointmentsState, AppointmentsUiModel>() {
    override fun invoke(state: AppointmentsState): AppointmentsUiModel =
        AppointmentsUiModel(
            data = kotlin.run {
               return@run mutableListOf<DiffItem>().apply {
                   state.appointments?.run {
                       addAll(state.appointments.map {
                           Timber.d("MAP $it")
                          PatientAppointmentUIModel(
                              id = it.id,
                              startDate = it.startDate.toString(),
                              duration = it.minutesDuration?.let {minutes-> "%d".format(minutes) }?:"",
                              isTelemed = it.type.contains("online",true),
                              comment = it.comment?:""
                          )
                       })
                   }
                }
            }
        )


}
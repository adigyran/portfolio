package com.aya.digital.core.feature.tabviews.appointments.ui.model

import android.content.Context
import com.aya.digital.core.feature.tabviews.appointments.viewmodel.AppointmentsState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.PatientAppointmentUIModel
import com.aya.digital.core.util.datetime.DateTimeUtils
import timber.log.Timber

class AppointmentsStateTransformer(private val context : Context, private val dateTimeUtils: DateTimeUtils): BaseStateTransformer<AppointmentsState, AppointmentsUiModel>() {
    override fun invoke(state: AppointmentsState): AppointmentsUiModel =
        AppointmentsUiModel(
            data = kotlin.run {
               return@run mutableListOf<DiffItem>().apply {
                   state.appointments?.run {
                       addAll(state.appointments.map {
                           Timber.d("MAP $it")
                          PatientAppointmentUIModel(
                              id = it.id,
                              startDate = dateTimeUtils.formatAppointmentDateTime(it.startDate),
                              duration = it.minutesDuration?.let {minutes-> "duration: %d min".format(minutes) }?:"",
                              isTelemed = it.type.contains("online",true),
                              participantName = "with %s".format(it.participantName),
                              comment = it.comment?:""
                          )
                       })
                   }
                }
            }
        )


}
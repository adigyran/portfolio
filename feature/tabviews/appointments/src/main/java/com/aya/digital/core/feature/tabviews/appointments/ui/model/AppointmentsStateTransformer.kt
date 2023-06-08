package com.aya.digital.core.feature.tabviews.appointments.ui.model

import android.content.Context
import com.aya.digital.core.domain.base.models.appointment.AppointmentModel
import com.aya.digital.core.feature.tabviews.appointments.viewmodel.AppointmentData
import com.aya.digital.core.feature.tabviews.appointments.viewmodel.AppointmentDoctor
import com.aya.digital.core.feature.tabviews.appointments.viewmodel.AppointmentsState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.AppointmentUiStatus
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.PatientAppointmentUIModel
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.PatientAppointmentsStatusFooterUIModel
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.PatientAppointmentsStatusHeaderUIModel
import com.aya.digital.core.util.datetime.DateTimeUtils

class AppointmentsStateTransformer(
    private val context: Context,
    private val dateTimeUtils: DateTimeUtils
) : BaseStateTransformer<AppointmentsState, AppointmentsUiModel>() {
    override fun invoke(state: AppointmentsState): AppointmentsUiModel =
        AppointmentsUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    state.appointments?.run {
                        AppointmentModel.AppointmentStatus.values().forEach { status ->
                            val headersFooters = getHeadersAndFooters(status)
                            val appointments = state.appointments.filter { it.status == status }
                            add(headersFooters.first)
                            appendStatuses(appointments, state.expandedStatuses.contains(status))
                            add(headersFooters.second)
                        }
                    }
                }
            }
        )

    private fun MutableList<DiffItem>.appendStatuses(
        appointments: List<AppointmentData>,
        isExpanded: Boolean
    ) {
        if(isExpanded) {
            addAll(appointments.toAppointmentUiModels()?:return)
        } else
        {
            addAll(appointments.take(1).toAppointmentUiModels()?:return)
        }
    }

    private fun List<AppointmentData>.toAppointmentUiModels() = this.map { it.getAppointmentUiModel() }?:null
    private fun AppointmentData.getAppointmentUiModel(): PatientAppointmentUIModel {
        val doctorModel = this.doctor
        return PatientAppointmentUIModel(
            id = this.id,
            startDateTime = dateTimeUtils.formatAppointmentDateTime(
                this.startDate
            ),
            duration = "",
            doctorName = "Dr. %s, %s".format(
                doctorModel?.doctorLastName,
                doctorModel?.getClinic()
            ),
            doctorSpeciality = "%s".format(doctorModel?.getSpeciality()),
            participantAvatarLink = doctorModel?.doctorAvatarImageLink,
            isTelemed = this.isTelemed,
            status = this.status.mapToUiStatus()
        )
    }

    private fun getHeadersAndFooters(status: AppointmentModel.AppointmentStatus) = status.run {
        Pair(
            PatientAppointmentsStatusHeaderUIModel(status = status.mapToUiStatus()),
            PatientAppointmentsStatusFooterUIModel(status = status.mapToUiStatus()),
        )
    }

    private fun AppointmentModel.AppointmentStatus.mapToUiStatus() =
        when (this) {
            AppointmentModel.AppointmentStatus.SCHEDULED -> AppointmentUiStatus.SCHEDULED
            AppointmentModel.AppointmentStatus.CANCELLED -> AppointmentUiStatus.CANCELLED
            AppointmentModel.AppointmentStatus.DONE -> AppointmentUiStatus.DONE
        }

    private fun AppointmentDoctor.getClinic() = doctorClinics?.firstOrNull()?.clinicName ?: ""
    private fun AppointmentDoctor.getSpeciality() = doctorSpecialities?.firstOrNull()?.name ?: ""


}
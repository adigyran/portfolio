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
                            addAll(appointments.map { it.getAppointmentUiModel() ?: return@run })
                            add(headersFooters.second)
                        }
                        /*state.appointments
                            .groupBy { it.status }
                            .forEach { entry ->
                                addAll(
                                    entry.value
                                        .map { appointment ->
                                            val doctorModel = appointment.doctor ?: return@run
                                            PatientAppointmentUIModel(
                                                id = appointment.id,
                                                startDateTime = dateTimeUtils.formatAppointmentDateTime(
                                                    appointment.startDate
                                                ),
                                                duration = "",
                                                doctorName = "Dr. %s, %s".format(
                                                    doctorModel.doctorLastName,
                                                    doctorModel.getClinic()
                                                ),
                                                doctorSpeciality = "%s".format(doctorModel.getSpeciality()),
                                                participantAvatarLink = doctorModel.doctorAvatarImageLink,
                                                isTelemed = appointment.isTelemed,
                                                status = when (appointment.status) {
                                                    AppointmentModel.AppointmentStatus.SCHEDULED -> PatientAppointmentUIModel.AppointmentStatus.SCHEDULED
                                                    AppointmentModel.AppointmentStatus.CANCELLED -> PatientAppointmentUIModel.AppointmentStatus.CANCELLED
                                                    AppointmentModel.AppointmentStatus.DONE -> PatientAppointmentUIModel.AppointmentStatus.DONE
                                                }
                                            )
                                        }
                                )
                            }*/
                    }
                }
            }
        )

    private fun AppointmentData.getAppointmentUiModel(): PatientAppointmentUIModel? {
        val doctorModel = this.doctor ?: return null
        return PatientAppointmentUIModel(
            id = this.id,
            startDateTime = dateTimeUtils.formatAppointmentDateTime(
                this.startDate
            ),
            duration = "",
            doctorName = "Dr. %s, %s".format(
                doctorModel.doctorLastName,
                doctorModel.getClinic()
            ),
            doctorSpeciality = "%s".format(doctorModel.getSpeciality()),
            participantAvatarLink = doctorModel.doctorAvatarImageLink,
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
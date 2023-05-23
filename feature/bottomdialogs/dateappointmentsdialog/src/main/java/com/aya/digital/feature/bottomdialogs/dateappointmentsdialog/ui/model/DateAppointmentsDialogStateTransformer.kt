package com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.ui.model

import android.content.Context
import android.text.format.DateUtils
import com.aya.digital.core.domain.base.models.appointment.AppointmentModel
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.AppointmentUiStatus
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.PatientAppointmentUIModel
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.PatientEmptyAppointmentUIModel
import com.aya.digital.core.util.datetime.DateTimeUtils
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.FieldsTags
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.viewmodel.AppointmentDoctor

import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.viewmodel.DateAppointmentsDialogState
import kotlinx.datetime.*

class DateAppointmentsDialogStateTransformer(
    private val context: Context,
    private val dateTimeUtils: DateTimeUtils
) : BaseStateTransformer<DateAppointmentsDialogState, DateAppointmentsDialogDialogUiModel>() {
    override fun invoke(state: DateAppointmentsDialogState): DateAppointmentsDialogDialogUiModel =
        DateAppointmentsDialogDialogUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    state.appointments?.run {
                        if (state.appointments.isEmpty()) {
                            add(
                                PatientEmptyAppointmentUIModel(
                                    id = 0,
                                    date = dateTimeUtils.formatAppointmentDateTime(
                                        state.date.atTime(
                                            0,
                                            0,
                                            0
                                        )
                                    ).first
                                )
                            )
                        } else {
                            addAll(state.appointments.map {appointment ->
                                val doctorModel = appointment.doctor ?: return@run
                                PatientAppointmentUIModel(
                                    id = appointment.id,
                                    startDateTime = dateTimeUtils.formatAppointmentDateTime(appointment.startDate),
                                    duration = "",
                                    doctorName = "Dr. %s, %s".format(
                                        doctorModel.doctorLastName,
                                        doctorModel.getClinic()
                                    ),
                                    doctorSpeciality = "%s".format(doctorModel.getSpeciality()),
                                    participantAvatarLink = doctorModel.doctorAvatarImageLink,
                                    isTelemed = appointment.isTelemed,
                                    status = when (appointment.status) {
                                        AppointmentModel.AppointmentStatus.SCHEDULED -> AppointmentUiStatus.SCHEDULED
                                        AppointmentModel.AppointmentStatus.CANCELLED -> AppointmentUiStatus.CANCELLED
                                        AppointmentModel.AppointmentStatus.DONE -> AppointmentUiStatus.DONE
                                    }
                                )
                            })
                        }
                    } ?: run {
                        add(
                            PatientEmptyAppointmentUIModel(
                                id = 0,
                                date = dateTimeUtils.formatAppointmentDateTime(
                                    state.date.atTime(
                                        0,
                                        0,
                                        0
                                    )
                                ).first
                            )
                        )
                    }
                }
            }
        )

    private fun AppointmentDoctor.getClinic() = doctorClinics?.firstOrNull()?.clinicName ?: ""
    private fun AppointmentDoctor.getSpeciality() = doctorSpecialities?.firstOrNull()?.name ?: ""
}
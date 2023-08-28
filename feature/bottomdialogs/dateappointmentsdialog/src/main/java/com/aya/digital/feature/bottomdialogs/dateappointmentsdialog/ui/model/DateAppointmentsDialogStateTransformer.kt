package com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.ui.model

import android.content.Context
import com.aya.digital.core.domain.base.models.appointment.AppointmentModel
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.AppointmentUiStatus
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.DoctorAppointmentUIModel
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.PatientAppointmentUIModel
import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.PatientEmptyAppointmentUIModel
import com.aya.digital.core.util.datetime.DateTimeUtils
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.ui.DateAppointmentsDialogView
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.viewmodel.AppointmentData
import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.viewmodel.AppointmentDoctor

import com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.viewmodel.DateAppointmentsDialogState
import kotlinx.datetime.*

class DateAppointmentsDialogStateTransformer(
    private val context: Context,
    private val param: DateAppointmentsDialogView.Param,
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
                                        state.dateTime.toKotlinLocalDateTime()
                                    ).first
                                )
                            )
                        } else {
                            addAll(state.appointments.map {appointment -> appointment.getAppointmentUiModel()?:return@apply})
                        }
                    } ?: run {
                        add(
                            PatientEmptyAppointmentUIModel(
                                id = 0,
                                date = dateTimeUtils.formatAppointmentDateTime(
                                    state.dateTime.toKotlinLocalDateTime()
                                ).first
                            )
                        )
                    }
                }
            }
        )

    private fun AppointmentDoctor.getClinic() = doctorClinics?.firstOrNull()?.clinicName ?: ""
    private fun AppointmentDoctor.getSpeciality() = doctorSpecialities?.firstOrNull()?.name ?: ""

    private fun AppointmentData.getAppointmentUiModel():DiffItem? {
        when
        {
            doctor!=null -> { val doctorModel = this.doctor ?: return null
                return PatientAppointmentUIModel(
                    id = id,
                    startDateTime = dateTimeUtils.formatAppointmentDateTime(startDate.toKotlinLocalDateTime()),
                    duration = "",
                    doctorName = "Dr. %s, %s".format(
                        doctorModel.doctorLastName,
                        doctorModel.getClinic()
                    ),
                    doctorSpeciality = "%s".format(doctorModel.getSpeciality()),
                    participantAvatarLink = doctorModel.doctorAvatarImageLink,
                    isTelemed = isTelemed,
                    status = when (status) {
                        AppointmentModel.AppointmentStatus.SCHEDULED -> AppointmentUiStatus.SCHEDULED
                        AppointmentModel.AppointmentStatus.CANCELLED -> AppointmentUiStatus.CANCELLED
                        AppointmentModel.AppointmentStatus.DONE -> AppointmentUiStatus.DONE
                    }
                )}
            patient!=null -> {
                val patientModel = this.patient?:return null
                return DoctorAppointmentUIModel(
                    id = id,
                    startDateTime = dateTimeUtils.formatAppointmentDateTime(startDate.toKotlinLocalDateTime()),
                    name = "%s %s".format(patientModel.firstName,patientModel.lastName),
                    age = "",
                    duration = "",
                    participantAvatarLink = patientModel.patientAvatarImageLink,
                    isTelemed = isTelemed,
                    status = when (status) {
                        AppointmentModel.AppointmentStatus.SCHEDULED -> AppointmentUiStatus.SCHEDULED
                        AppointmentModel.AppointmentStatus.CANCELLED -> AppointmentUiStatus.CANCELLED
                        AppointmentModel.AppointmentStatus.DONE -> AppointmentUiStatus.DONE
                    }
                )
            }
            else -> return null
        }
    }
}
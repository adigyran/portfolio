package com.aya.digital.core.feature.appointments.appointmentcard.ui.model

import android.content.Context
import com.aya.digital.core.ext.strings
import com.aya.digital.core.feature.appointments.appointmentcard.R
import com.aya.digital.core.feature.appointments.appointmentcard.viewmodel.AppointmentCardState
import com.aya.digital.core.feature.appointments.appointmentcard.viewmodel.DoctorData
import com.aya.digital.core.feature.appointments.appointmentcard.viewmodel.PatientData
import com.aya.digital.core.localisation.R as LocR
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.navigation.AppFlavour
import com.aya.digital.core.navigation.Flavor
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.AppointmentTelemedUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.DoctorDetailsBioUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.DoctorDetailsInsuranceUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.DoctorDetailsLocationUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.DoctorDetailsTitleUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.AppointmentTelemedDelegate
import com.aya.digital.core.util.datetime.DateTimeUtils
import kotlinx.datetime.*
import java.time.temporal.ChronoUnit

internal class AppointmentCardStateTransformer(
    private val context: Context,
    private val dateTimeUtils: DateTimeUtils,
    private val appFlavour: AppFlavour
) :
    BaseStateTransformer<AppointmentCardState, AppointmentCardUiModel>() {


    override fun invoke(state: AppointmentCardState): AppointmentCardUiModel =
        AppointmentCardUiModel(
            appointmentDateTimeText = state.appointmentDate?.run {
                dateTimeUtils.formatAppointmentCardDateTime(
                    this
                )
            },
            participantAvatarLink = state.participantAvatar,
            participantLines = getParticipantLines(state),
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    addAll(getCardItems(state))
                }
            }
        )

    private fun getCardItems(state: AppointmentCardState): List<DiffItem> =
        mutableListOf<DiffItem>().apply {
            state.isTelemed?.let { telemed ->
                if (telemed) add(AppointmentTelemedUIModel())
            }
            state.appointmentComment?.let { comment ->
                add(DoctorDetailsTitleUIModel(getCommentTitle()))
                add(DoctorDetailsBioUIModel(bio = comment, isExpanded = state.commentReadMore))
            }
            if (appFlavour.flavour == Flavor.Patient) {
                state.doctorData?.let { doctorData ->
                    doctorData.doctorAddress?.let { address ->
                        add(DoctorDetailsTitleUIModel(context.strings[LocR.string.appointment_card_location]))
                        add(DoctorDetailsLocationUIModel(address = address))
                    }
                }
            }
            when (appFlavour.flavour) {
                Flavor.Doctor -> {
                    state.patientData?.let { patientData ->
                        patientData.patientInsurances?.let { insuranceModels ->
                            add(DoctorDetailsTitleUIModel(context.strings[LocR.string.appointment_card_insurances]))
                            addAll(insuranceModels.map { DoctorDetailsInsuranceUIModel(it.name) })
                        }
                    }
                }

                Flavor.Patient -> {
                    state.doctorData?.let { patientData ->
                        patientData.doctorInsurances?.let { insuranceModels ->
                            add(DoctorDetailsTitleUIModel(context.strings[LocR.string.appointment_card_insurances]))
                            addAll(insuranceModels.map { DoctorDetailsInsuranceUIModel(it.name) })
                        }
                    }
                }
            }

        }


    private fun getCommentTitle(): String =
        when (appFlavour.flavour) {
            is Flavor.Patient -> context.strings[LocR.string.appointment_card_my_comment]
            is Flavor.Doctor -> context.strings[LocR.string.appointment_card_patient_comment]
            else -> "unknown"
        }

    private fun getParticipantLines(state: AppointmentCardState): Pair<String, String>? =
        when (appFlavour.flavour) {
            is Flavor.Patient -> getDoctorLines(state.doctorData)
            is Flavor.Doctor -> getPatientLines(state.patientData)
            else -> Pair("unknown", "unknown")
        }

    private fun getPatientLines(patientData: PatientData?): Pair<String, String>? =
        patientData?.run {
            val firstLine =
                context.strings[LocR.string.appointment_card_patient_lines].format(patientData.patientFirstName, patientData.patientLastName)
            val secondLine = patientData?.patientBirthDate?.getAge() ?: ""
            Pair(firstLine, secondLine)
        }

    private fun getDoctorLines(doctorData: DoctorData?): Pair<String, String>? =
        doctorData?.run {
            val specialityName = doctorData.doctorSpecialities?.firstOrNull()?.name
            val firstLine = context.strings[LocR.string.appointment_card_doctor_lines_first_line].format(specialityName)
            val lastName = doctorData.doctorLastName
            val clinicName = doctorData.doctorClinics?.firstOrNull()?.clinicName
            val secondLine = context.strings[LocR.string.appointment_card_doctor_lines_second_line].format(lastName, clinicName)
            Pair(firstLine, secondLine)
        }

    private fun LocalDate.getAge() =
        context.strings[LocR.string.age].format(ChronoUnit.YEARS.between(this.toJavaLocalDate(), java.time.LocalDate.now()).toString())

}
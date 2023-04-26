package com.aya.digital.core.feature.doctors.doctorcard.ui.model

import android.content.Context
import android.text.format.DateUtils
import com.aya.digital.core.domain.schedule.base.model.ScheduleSlotModel
import com.aya.digital.core.feature.doctors.doctorcard.DoctorCardMode
import com.aya.digital.core.feature.doctors.doctorcard.viewmodel.DoctorCardState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.DoctorDetailsBioUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.DoctorDetailsInsuranceUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.DoctorDetailsLocationUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.DoctorDetailsTitleUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.model.DoctorDateTitleUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.model.DoctorSlotUIModel
import com.aya.digital.core.util.datetime.DateTimeUtils
import kotlinx.datetime.*

class DoctorCardStateTransformer(
    private val context: Context,
    private val dateTimeUtils: DateTimeUtils
) :
    BaseStateTransformer<DoctorCardState, DoctorCardUiModel>() {
    override fun invoke(state: DoctorCardState): DoctorCardUiModel =
        DoctorCardUiModel(
            doctorCardMode = state.doctorCardMode,
            doctorName = "Dr. %s".format(state.doctorLastName.getField()),
            doctorSpeciality = state.doctorSpecialities?.let { specialityModels ->
                specialityModels.firstOrNull()?.name ?: getNotSpecifiedText()
            } ?: getNotSpecifiedText(),
            doctorClinic = state.doctorClinics?.let { clinicModels ->
                clinicModels.firstOrNull()?.clinicName ?: getNotSpecifiedText()
            } ?: getNotSpecifiedText(),
            doctorAvatar = state.doctorAvatar,
            doctorAddress = state.doctorAddress.getField(),
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    addAll(
                        when (state.doctorCardMode) {
                            DoctorCardMode.ShowingDetailsInfo -> {
                                getDetailsData(state)
                            }

                            DoctorCardMode.ShowingSlots -> {
                                getSlotsData(state)
                            }
                        }
                    )
                }
            }
        )

    private fun getSlotsData(state: DoctorCardState): List<DiffItem> =
        mutableListOf<DiffItem>().apply {
            state.doctorSlots?.let { scheduleSlotModels -> addAll(calculateSlots(scheduleSlotModels)) }
        }

    private fun calculateSlots(scheduleSlotModels: List<ScheduleSlotModel>): List<DiffItem> =
        mutableListOf<DiffItem>().apply {
            scheduleSlotModels
                .sortedWith { t, t2 -> t.startDate.compareTo(t2.startDate) }
                .groupBy { it.startDate.dayOfMonth }
                .forEach { entry ->
                    val firstSlot = entry.value.first()
                    val isTelemed = firstSlot.type.contains("online", ignoreCase = true)
                    val firstSlotDateTime = firstSlot.startDate
                    val slotTitle = "%s, %s".format(
                        firstSlotDateTime.getRelativeText(),
                        dateTimeUtils.formatSlotTitleDate(firstSlotDateTime)
                    )
                    add(
                        DoctorDateTitleUIModel(
                            id = entry.key,
                            dateText = slotTitle,
                            isTelemed = isTelemed
                        )
                    )
                    addAll(entry.value
                        .take(7)
                        .map {
                            DoctorSlotUIModel(
                                it.id,
                                dateTimeUtils.formatSlotTime(it.startDate.time)
                            )
                        })
                    if (entry.value.size > 7) add(
                        DoctorSlotUIModel(
                            entry.key,
                            "more",
                            firstSlotDateTime.date
                        )
                    )
                }
        }

    private fun getDetailsData(state: DoctorCardState): List<DiffItem> =
        mutableListOf<DiffItem>().apply {
            state.doctorBio?.let { bio ->
                add(DoctorDetailsTitleUIModel("Doctor’s BIO"))
                add(DoctorDetailsBioUIModel(bio = bio, isExpanded = state.bioReadMore))
            }
            state.doctorInsurances?.let { insurances ->
                add(DoctorDetailsTitleUIModel("Insurances"))
                addAll(insurances.map { DoctorDetailsInsuranceUIModel(it.name) })
            }
            state.doctorAddress?.let { address ->
                add(DoctorDetailsTitleUIModel("Location"))
                add(DoctorDetailsLocationUIModel(address = address))
            }

        }

    private fun getNotSpecifiedText() = "Not specified"
    private fun String?.getField() = this ?: getNotSpecifiedText()

    private fun LocalDateTime.getRelativeText() = DateUtils.getRelativeTimeSpanString(
        this.toInstant(
            TimeZone.currentSystemDefault()
        ).toEpochMilliseconds(),
        System.currentTimeMillis(),
        DateUtils.DAY_IN_MILLIS,
        DateUtils.FORMAT_ABBREV_RELATIVE
    );

}
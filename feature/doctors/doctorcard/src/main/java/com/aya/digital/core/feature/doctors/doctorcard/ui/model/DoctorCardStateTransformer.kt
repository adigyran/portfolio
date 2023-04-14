package com.aya.digital.core.feature.doctors.doctorcard.ui.model

import android.content.Context
import com.aya.digital.core.domain.schedule.base.model.ScheduleSlotModel
import com.aya.digital.core.feature.doctors.doctorcard.DoctorCardMode
import com.aya.digital.core.feature.doctors.doctorcard.viewmodel.DoctorCardState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.DoctorDetailsBioUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.DoctorDetailsInsuranceUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.model.DoctorDetailsTitleUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctordetails.ui.DoctorDetailsTitleDelegate
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.model.DoctorDateTitleUIModel
import com.aya.digital.core.ui.delegates.doctorcard.doctorslot.model.DoctorSlotUIModel

class DoctorCardStateTransformer(private val context: Context) :
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
                    val firstSlotDate = entry.value.first().startDate
                    add(DoctorDateTitleUIModel(entry.key,firstSlotDate.toString()))
                    addAll(entry.value.map { DoctorSlotUIModel(it.id,it.startDate.toString()) })
                }
        }

    private fun getDetailsData(state: DoctorCardState): List<DiffItem> =
        mutableListOf<DiffItem>().apply {
            state.doctorBio?.let { bio ->
                add(DoctorDetailsTitleUIModel("Doctor bio"))
                add(DoctorDetailsBioUIModel(bio = bio))
            }
            state.doctorInsurances?.let { insurances ->
                add(DoctorDetailsTitleUIModel("Served insurances"))
                addAll(insurances.map { DoctorDetailsInsuranceUIModel(it.name) })
            }

        }

    private fun getNotSpecifiedText() = "Not specified"
    private fun String?.getField() = this ?: getNotSpecifiedText()

}
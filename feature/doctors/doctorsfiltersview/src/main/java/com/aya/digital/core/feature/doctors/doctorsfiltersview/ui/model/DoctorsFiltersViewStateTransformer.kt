package com.aya.digital.core.feature.doctors.doctorsfiltersview.ui.model

import android.content.Context
import android.text.format.DateUtils
import com.aya.digital.core.domain.schedule.base.model.ScheduleSlotModel
import com.aya.digital.core.feature.doctors.doctorsfiltersview.viewmodel.DoctorCardState
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

class DoctorsFiltersViewStateTransformer(
    private val context: Context,
    private val dateTimeUtils: DateTimeUtils
) :
    BaseStateTransformer<DoctorCardState, DoctorsFiltersViewUiModel>() {
    override fun invoke(state: DoctorCardState): DoctorsFiltersViewUiModel =
        DoctorsFiltersViewUiModel(

        )


}
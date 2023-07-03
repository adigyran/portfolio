package com.aya.digital.core.feature.doctors.doctorsfiltersview.ui.model

import android.content.Context
import com.aya.digital.core.feature.doctors.doctorsfiltersview.viewmodel.DoctorsFiltersViewState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.util.datetime.DateTimeUtils

class DoctorsFiltersViewStateTransformer(
    private val context: Context,
    private val dateTimeUtils: DateTimeUtils
) :
    BaseStateTransformer<DoctorsFiltersViewState, DoctorsFiltersViewUiModel>() {
    override fun invoke(state: DoctorsFiltersViewState): DoctorsFiltersViewUiModel =
        DoctorsFiltersViewUiModel(

        )


}
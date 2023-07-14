package com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class DoctorSearchMapUiModel(
    val data: List<DiffItem>? = null,
    val specialityFilterText: String? = null,
    val locationFilterText: String? = null,
    val insuranceFilterText: String? = null
) : BaseUiModel

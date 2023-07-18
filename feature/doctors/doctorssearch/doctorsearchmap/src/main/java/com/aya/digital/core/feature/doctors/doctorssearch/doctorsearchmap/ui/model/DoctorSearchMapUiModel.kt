package com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.ui.model

import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

internal data class DoctorSearchMapUiModel(
    val data: List<DoctorMarkerModel>? = null,
    val specialityFilterText: String? = null,
    val locationFilterText: String? = null,
    val insuranceFilterText: String? = null
) : BaseUiModel

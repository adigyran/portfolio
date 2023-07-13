package com.aya.digital.core.feature.tabviews.doctorsearch.ui.model

import com.aya.digital.core.feature.tabviews.doctorsearch.DoctorSearchMode
import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class DoctorSearchUiModel(
    val doctorSearchMode: DoctorSearchMode = DoctorSearchMode.ShowingAllDoctors,
    val data: List<DiffItem>? = null,
    val specialityFilterText: String? = null,
    val locationFilterText: String? = null,
    val insuranceFilterText: String? = null
) : BaseUiModel

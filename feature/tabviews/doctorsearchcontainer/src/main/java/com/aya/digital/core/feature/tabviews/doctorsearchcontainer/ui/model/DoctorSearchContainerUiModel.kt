package com.aya.digital.core.feature.tabviews.doctorsearchcontainer.ui.model

import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.DoctorSearchMode
import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class DoctorSearchContainerUiModel(
    val doctorSearchMode: DoctorSearchMode = DoctorSearchMode.ShowingAllDoctors,
    val data: List<DiffItem>? = null,
    val specialityFilterText: String? = null,
    val locationFilterText: String? = null,
    val insuranceFilterText: String? = null
) : BaseUiModel

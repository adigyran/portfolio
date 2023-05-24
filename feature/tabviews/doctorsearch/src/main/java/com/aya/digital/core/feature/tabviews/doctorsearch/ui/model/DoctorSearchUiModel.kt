package com.aya.digital.core.feature.tabviews.doctorsearch.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class DoctorSearchUiModel(
    val data: List<DiffItem>? = null,
    val specialityFilterText: String? = null,
    val locationFilterText: String? = null,
    val insuranceFilterText: String? = null
) : BaseUiModel

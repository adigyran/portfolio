package com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.ui.model

import com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.DoctorSearchListMode
import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class DoctorSearchListUiModel(
    val doctorSearchMode: DoctorSearchListMode = DoctorSearchListMode.ShowingAllDoctors,
    val data: List<DiffItem>? = null,
    val specialityFilterText: String? = null,
    val locationFilterText: String? = null,
    val insuranceFilterText: String? = null,
    val filtersEnabled:Boolean=false
) : BaseUiModel

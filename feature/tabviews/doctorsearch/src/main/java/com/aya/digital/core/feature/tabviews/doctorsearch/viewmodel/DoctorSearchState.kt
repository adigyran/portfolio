package com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel

import com.aya.digital.core.data.base.dataprocessing.dataloading.DataLoadingOperationWithPagination
import com.aya.digital.core.data.base.result.models.dictionaries.MultiSelectResultModel
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.feature.tabviews.doctorsearch.DoctorSearchMode
import com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel.model.SelectedFilterModel
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class DoctorSearchState(
    val doctorSearchMode: DoctorSearchMode = DoctorSearchMode.ShowingAllDoctors,
    val cursor: String? = null,
    val doctors: List<DoctorModel>? = null,
    val favoriteDoctors: List<Int>? = null,
    val dataOperation: DataLoadingOperationWithPagination,
    val selectedFilters: Set<SelectedFilterModel> = setOf(),
) :
    BaseState

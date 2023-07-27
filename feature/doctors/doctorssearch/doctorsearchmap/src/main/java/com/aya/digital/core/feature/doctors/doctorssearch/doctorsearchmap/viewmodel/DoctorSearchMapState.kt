package com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.viewmodel

import android.location.Location
import com.aya.digital.core.data.base.dataprocessing.dataloading.DataLoadingOperationWithPagination
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.viewmodel.model.SelectedFilterModel
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class DoctorSearchMapState(
    val cursor: String? = null,
    val location: Location? = null,
    val doctors: List<DoctorModel>? = null,
    val selectedDoctor:DoctorModel? = null,
    val favoriteDoctors: List<Int>? = null,
    val dataOperation: DataLoadingOperationWithPagination,
    val selectedFilters: Set<SelectedFilterModel> = setOf(),
) :
    BaseState

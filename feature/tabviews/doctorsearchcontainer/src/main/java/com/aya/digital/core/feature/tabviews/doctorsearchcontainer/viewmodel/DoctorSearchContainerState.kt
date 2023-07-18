package com.aya.digital.core.feature.tabviews.doctorsearchcontainer.viewmodel

import com.aya.digital.core.data.base.dataprocessing.dataloading.DataLoadingOperationWithPagination
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.CurrentMode
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.viewmodel.model.SelectedFilterModel
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class DoctorSearchContainerState(
    val currentMode: CurrentMode = CurrentMode.List
) :
    BaseState

package com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel

import com.aya.digital.core.data.base.dataprocessing.dataloading.DataLoadingOperationWithPagination
import com.aya.digital.core.domain.doctors.base.model.DoctorModel
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class DoctorSearchState(val cursor: String? = null,
                             val doctors: List<DoctorModel>? = null,
                             val dataOperation:DataLoadingOperationWithPagination
                             ) :
    BaseState

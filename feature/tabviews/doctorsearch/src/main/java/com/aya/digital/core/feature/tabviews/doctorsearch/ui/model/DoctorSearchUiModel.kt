package com.aya.digital.core.feature.tabviews.doctorsearch.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class DoctorSearchUiModel(val data: List<DiffItem>? = null) : BaseUiModel

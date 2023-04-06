package com.aya.digital.core.feature.tabviews.appointments.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class AppointmentsUiModel(val data: List<DiffItem>? = null) : BaseUiModel

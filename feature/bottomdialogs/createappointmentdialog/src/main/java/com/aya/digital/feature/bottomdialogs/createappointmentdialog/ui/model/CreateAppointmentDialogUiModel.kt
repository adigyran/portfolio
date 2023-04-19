package com.aya.digital.feature.bottomdialogs.createappointmentdialog.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class CreateAppointmentDialogUiModel(
    val data: List<DiffItem>? = null,
    val selectedSlotId: Int? = null,
    val comment: String? = null
) : BaseUiModel

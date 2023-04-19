package com.aya.digital.feature.bottomdialogs.createappointmentdialog.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class CreateAppointmentDialogUiModel(
    val data: List<DiffItem>? = null,
    val comment: String? = null,
    val dateText:String? = null
) : BaseUiModel

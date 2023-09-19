package com.aya.digital.feature.bottomdialogs.createscheduledialog.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class CreateScheduleDialogUiModel(
    val data: List<DiffItem>? = null,
    val createEnabled:Boolean = false
) : BaseUiModel

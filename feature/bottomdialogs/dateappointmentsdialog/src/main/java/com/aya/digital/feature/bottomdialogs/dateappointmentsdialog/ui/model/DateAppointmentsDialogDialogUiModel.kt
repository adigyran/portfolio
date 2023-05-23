package com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class DateAppointmentsDialogDialogUiModel(
    val data: List<DiffItem>? = null
) : BaseUiModel

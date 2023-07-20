package com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class DoctorsClusterListDialogUiModel(
    val data: List<DiffItem>? = null
) : BaseUiModel

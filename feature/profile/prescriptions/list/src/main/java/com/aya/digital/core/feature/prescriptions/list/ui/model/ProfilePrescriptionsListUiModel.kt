package com.aya.digital.core.feature.prescriptions.list.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class ProfilePrescriptionsListUiModel(val data: List<DiffItem>? = null) : BaseUiModel

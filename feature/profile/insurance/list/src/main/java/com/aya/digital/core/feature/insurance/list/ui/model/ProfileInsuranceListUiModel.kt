package com.aya.digital.core.feature.insurance.list.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class ProfileInsuranceListUiModel(val data: List<DiffItem>? = null) : BaseUiModel

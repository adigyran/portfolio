package com.aya.digital.core.feature.profile.insurance.add.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class ProfileInsuranceAddUiModel(val saveAddButtonText:String, val data: List<DiffItem>? = null) : BaseUiModel

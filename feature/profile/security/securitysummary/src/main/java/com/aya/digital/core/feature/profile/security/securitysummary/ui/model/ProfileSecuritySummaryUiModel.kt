package com.aya.digital.core.feature.profile.security.securitysummary.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class ProfileSecuritySummaryUiModel(val data: List<DiffItem>? = null) : BaseUiModel

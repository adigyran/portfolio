package com.aya.digital.feature.auth.chooser.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class AuthChooserUiModel(val data: List<DiffItem>? = null) : BaseUiModel

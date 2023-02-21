package com.aya.digital.feature.auth.signup.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class SignUpUiModel(val data: List<DiffItem>? = null) : BaseUiModel

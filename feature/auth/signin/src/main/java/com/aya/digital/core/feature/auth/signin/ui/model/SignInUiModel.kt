package com.aya.digital.core.feature.auth.signin.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class SignInUiModel(val data: List<DiffItem>? = null) : BaseUiModel

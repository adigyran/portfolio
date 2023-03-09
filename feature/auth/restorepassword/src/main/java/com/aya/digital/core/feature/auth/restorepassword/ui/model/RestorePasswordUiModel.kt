package com.aya.digital.core.feature.auth.restorepassword.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

internal data class RestorePasswordUiModel(val buttonText:String, val data: List<DiffItem>? = null) : BaseUiModel

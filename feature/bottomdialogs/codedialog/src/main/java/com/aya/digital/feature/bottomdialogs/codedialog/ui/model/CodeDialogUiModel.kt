package com.aya.digital.feature.bottomdialogs.codedialog.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class CodeDialogUiModel(val title:String, val description : String, val bottomDescription:String, val code:String) : BaseUiModel

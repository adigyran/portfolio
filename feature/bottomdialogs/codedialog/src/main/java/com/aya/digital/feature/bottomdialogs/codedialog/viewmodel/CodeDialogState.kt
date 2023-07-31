package com.aya.digital.feature.bottomdialogs.codedialog.viewmodel

import com.aya.digital.core.mvi.BaseState
import com.aya.digital.feature.bottomdialogs.codedialog.CodeDialogInputState
import kotlinx.parcelize.Parcelize

@Parcelize
data class CodeDialogState(val state: CodeDialogInputState, val code:String) : BaseState

package com.aya.digital.feature.bottomdialogs.codedialog.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class CodeDialogState(val email:String = "", val code:String) : BaseState

package com.aya.digital.core.feature.profile.emergencycontact.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class ProfileEmergencyContactUiModel(val buttonText:String,val data: List<DiffItem>? = null) : BaseUiModel

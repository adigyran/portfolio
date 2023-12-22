package com.aya.digital.core.feature.profile.prescriptions.edit.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class ProfilePrescriptionsEditUiModel(val saveAddButtonText:String, val data: List<DiffItem>? = null, val fullScreenPolicyUrl:String?) : BaseUiModel

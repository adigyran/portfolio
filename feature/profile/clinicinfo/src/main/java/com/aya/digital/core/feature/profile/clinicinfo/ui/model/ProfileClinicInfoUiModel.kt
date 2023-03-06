package com.aya.digital.core.feature.profile.clinicinfo.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class ProfileClinicInfoUiModel(val data: List<DiffItem>? = null) : BaseUiModel

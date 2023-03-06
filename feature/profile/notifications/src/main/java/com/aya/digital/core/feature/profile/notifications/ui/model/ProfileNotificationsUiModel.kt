package com.aya.digital.core.feature.profile.notifications.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class ProfileNotificationsUiModel(val data: List<DiffItem>? = null) : BaseUiModel

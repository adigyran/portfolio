package com.aya.digital.core.feature.tabviews.profile.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class ProfileUiModel(
    val avatar: String? = null,
    val name: String? = null,
    val subTitle: String? = null,
    val data: List<DiffItem>? = null
) : BaseUiModel

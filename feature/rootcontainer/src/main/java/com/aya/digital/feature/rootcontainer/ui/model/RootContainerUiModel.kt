package com.aya.digital.feature.rootcontainer.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

data class RootContainerUiModel(
  val inProgress:Boolean
) : BaseUiModel

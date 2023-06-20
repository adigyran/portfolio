package com.aya.digital.feature.rootcontainer.ui.model

import android.content.Context
import android.text.format.DateUtils
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.util.datetime.DateTimeUtils
import com.aya.digital.feature.rootcontainer.viewmodel.RootContainerState

class RootContainerStateTransformer(
    private val context: Context) :
    BaseStateTransformer<RootContainerState, RootContainerUiModel>() {
    override fun invoke(state: RootContainerState): RootContainerUiModel =
        RootContainerUiModel(
           inProgress = state.inProgress
        )



}
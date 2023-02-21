package com.aya.digital.core.feature.bottomnavhost.ui.model

import android.content.Context
import com.aya.digital.core.feature.bottomnavhost.viewmodel.BottomNavHostState
import com.aya.digital.core.mvi.BaseState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.mvi.StubUiModel

class BottomNavHostStateTransformer(context : Context): BaseStateTransformer<BottomNavHostState,StubUiModel>() {
    override fun invoke(state: BottomNavHostState): StubUiModel = StubUiModel


}
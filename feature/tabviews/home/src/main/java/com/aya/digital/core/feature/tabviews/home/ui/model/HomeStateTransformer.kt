package com.aya.digital.core.feature.tabviews.home.ui.model

import android.content.Context
import com.aya.digital.core.feature.tabviews.home.viewmodel.HomeState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem

class HomeStateTransformer(private val context : Context): BaseStateTransformer<HomeState, HomeUiModel>() {
    override fun invoke(state: HomeState): HomeUiModel =
        HomeUiModel(
            data = kotlin.run {
               return@run mutableListOf<DiffItem>().apply {

                }
            }
        )

    private fun getHomeButtons() = mutableListOf<DiffItem>().apply {

    }
    private fun getHomeNews() = HomeNew

}
package com.aya.digital.core.feature.tabviews.home.ui.model

import android.content.Context
import com.aya.digital.core.feature.tabviews.home.viewmodel.HomeState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.home.homeitems.model.HomeLastUpdatesBottomUIModel
import com.aya.digital.core.ui.delegates.home.homeitems.model.HomeLastUpdatesTopUIModel
import com.aya.digital.core.ui.delegates.home.homeitems.model.HomeNewsContainerUIModel
import com.aya.digital.core.ui.delegates.home.homeitems.model.HomeNewsUIModel

class HomeStateTransformer(private val context : Context): BaseStateTransformer<HomeState, HomeUiModel>() {
    override fun invoke(state: HomeState): HomeUiModel =
        HomeUiModel(
            data = kotlin.run {
               return@run mutableListOf<DiffItem>().apply {
                   addAll(getHomeButtons())
                   add(HomeNewsContainerUIModel(news = getHomeNews()))
                   addAll(getHomeUpdatesSection())
                }
            }
        )

    private fun getHomeButtons() = mutableListOf<DiffItem>().apply {

    }
    private fun getHomeNews() = mutableListOf<HomeNewsUIModel>().apply {
        add(
            HomeNewsUIModel(id = 111,
            title = "Sleep apnea treatment",
            date = "Aug 3, 2023",
            image = "https://i.imgur.com/1P1TgJC.jpeg"
        )
        )
        add(
            HomeNewsUIModel(id = 111,
            title = "Robotic liver transplant",
            date = "Aug 10, 2023",
            image = "https://i.imgur.com/JnhMcN7.jpeg"
        )
        )
        add(
            HomeNewsUIModel(id = 111,
            title = "AI predicts future pancreatic cancer",
            date = "Aug 20, 2023",
            image = "https://i.imgur.com/QtnJDdw.jpeg"
        )
        )
    }

   private fun getHomeUpdatesSection() = mutableListOf<DiffItem>().apply {
       add(HomeLastUpdatesTopUIModel())
       addAll(getHomeUpdates())
       add(HomeLastUpdatesBottomUIModel())
   }

    private fun getHomeUpdates() = mutableListOf<DiffItem>().apply {

    }

}
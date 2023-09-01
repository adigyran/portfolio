package com.aya.digital.core.feature.tabviews.home.ui.model

import android.content.Context
import com.aya.digital.core.feature.tabviews.home.ButtonsTags
import com.aya.digital.core.feature.tabviews.home.R
import com.aya.digital.core.feature.tabviews.home.viewmodel.HomeState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.home.homeitems.model.HomeButtonUIModel
import com.aya.digital.core.ui.delegates.home.homeitems.model.HomeClinicsUIModel
import com.aya.digital.core.ui.delegates.home.homeitems.model.HomeLastUpdatesBottomUIModel
import com.aya.digital.core.ui.delegates.home.homeitems.model.HomeLastUpdatesItemUIModel
import com.aya.digital.core.ui.delegates.home.homeitems.model.HomeLastUpdatesTitleUIModel
import com.aya.digital.core.ui.delegates.home.homeitems.model.HomeLastUpdatesTopUIModel
import com.aya.digital.core.ui.delegates.home.homeitems.model.HomeNewsContainerUIModel
import com.aya.digital.core.ui.delegates.home.homeitems.model.HomeNewsUIModel

class HomeStateTransformer(private val context : Context): BaseStateTransformer<HomeState, HomeUiModel>() {
    override fun invoke(state: HomeState): HomeUiModel =
        HomeUiModel(
            data = kotlin.run {
               return@run mutableListOf<DiffItem>().apply {
                   addAll(getHomeButtons(state))
                   add(HomeNewsContainerUIModel(news = getHomeNews()))
                   addAll(getHomeClinics())
                   addAll(getHomeUpdatesSection(state))
                }
            }
        )

    private fun getHomeButtons(state: HomeState) = mutableListOf<DiffItem>().apply {
        add(HomeButtonUIModel(
            tag = ButtonsTags.FIND_DOCTOR,
            title = "Find Doctor",
            descr = "%d doctors".format(state.doctorsCount),
            iconId = R.drawable.ic_finddoctor,
            enabled = true
        ))
        add(HomeButtonUIModel(
            tag = ButtonsTags.APPOINTMENTS,
            title = "Appointments",
            descr = "%d appointments".format(state.appointmentsCount),
            iconId = R.drawable.ic_appointments,
            enabled = true
        ))
        add(HomeButtonUIModel(
            tag = ButtonsTags.TELEMEDICINE,
            title = "Telemedicine",
            descr = "%d appointments".format(state.telemedicineCount),
            iconId = R.drawable.ic_appointments,
            enabled = true
        ))
        add(HomeButtonUIModel(
            tag = ButtonsTags.RECIPES,
            title = "Recipes",
            descr = "Coming soon",
            iconId = R.drawable.ic_recipes,
            enabled = false
        ))
        add(HomeButtonUIModel(
            tag = ButtonsTags.PHARMACIES,
            title = "Pharmacies",
            descr = "Coming soon",
            iconId = R.drawable.ic_pharmacy,
            enabled = false
        ))
        add(HomeButtonUIModel(
            tag = ButtonsTags.SERVICE_COST,
            title = "Service cost",
            descr = "Coming soon",
            iconId = R.drawable.ic_service,
            enabled = false
        ))
        add(HomeButtonUIModel(
            tag = ButtonsTags.DOCUMENTS,
            title = "Documents",
            descr = "Coming soon",
            iconId = R.drawable.ic_documents,
            enabled = false
        ))
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

   private fun getHomeUpdatesSection(state: HomeState) = mutableListOf<DiffItem>().apply {
       add(HomeLastUpdatesTopUIModel())
       addAll(getHomeUpdates(state))
       add(HomeLastUpdatesBottomUIModel())
   }

    private fun getHomeUpdates(state: HomeState) = mutableListOf<DiffItem>().apply {
        state.lastUpdates?.let {
            val list = mutableListOf<DiffItem>()
            it.forEach { entry ->
                list.add(HomeLastUpdatesTitleUIModel(entry.value.title))
                list.addAll(entry.value.items.map { item-> HomeLastUpdatesItemUIModel(item.text) })
            }
            addAll(list)
        }
    }

    private fun getHomeClinics() = mutableListOf<DiffItem>().apply {
        add(HomeClinicsUIModel(
            clinicsCount = "3 clinics",
            mapImageId = R.drawable.map
        ))
    }

}
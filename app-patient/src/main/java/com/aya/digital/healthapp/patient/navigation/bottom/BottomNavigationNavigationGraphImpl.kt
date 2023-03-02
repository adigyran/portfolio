package com.aya.digital.healthapp.patient.navigation.bottom

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.aya.digital.core.ext.inTransaction
import com.aya.digital.core.navigation.graph.navigator.BottomNavigationGraph
import com.aya.digital.core.navigation.screen.HealthAppTabFragmentScreen
import com.aya.digital.core.ui.base.screens.DiTabContainerFragment
import com.aya.digital.core.ui.base.utils.*
import com.aya.digital.feature.tabs.appointments.navigation.AppointmentsTabScreen
import com.aya.digital.feature.tabs.doctorsearch.navigation.DoctorSearchTabScreen
import com.aya.digital.feature.tabs.home.navigation.HomeTabScreen
import com.aya.digital.feature.tabs.profile.navigation.ProfileTabScreen
import com.aya.digital.healthapp.patient.R

class BottomNavigationNavigationGraphImpl : BottomNavigationGraph {
    override fun openTab(
        containerId: Int,
        fragment: Fragment,
        screen: HealthAppTabFragmentScreen,
        fragmentFactory: FragmentFactory
    ): Int {

        val screensPair = listOf(
            Pair(HomeTabScreen, Tags.HOME_TAB_TAG),
            Pair(AppointmentsTabScreen, Tags.APPOINTMENT_TAB_TAG),
            Pair(DoctorSearchTabScreen, Tags.DOCTOR_SEARCH_TAB_TAG),
            Pair(ProfileTabScreen, Tags.PROFILE_TAB_TAG),
        )
        val screenViews = Tags.getTags().map { tag ->
            val screenPair = screensPair.first { it.second == tag }
            ScreenView(
                screen = screenPair.first,
                view = fragment.childFragmentManager.getFragmentByTag(screenPair.second),
                tag = screenPair.second
            )
        }
        fragment.childFragmentManager.inTransaction {
            screenViews.filter { it.screen != screen }.forEach { screenView ->
                hideFragment(this, screenView.view)
            }
            this
        }
        fragment.childFragmentManager.executePendingTransactions()

        fragment.childFragmentManager.inTransaction {
            screenViews.filter { it.screen == screen }.forEach { screenView ->
                showFragment(this, screenView.view)
            }
            this
        }

        fragment.childFragmentManager.executePendingTransactions()
        fragment.childFragmentManager.inTransaction {
            screenViews.filter {!checkFragmentNotNull(it.view) && it.screen == screen}
                .forEach { this.addFragment(it.screen,fragmentFactory,containerId,it.tag) }
            this
        }
        return getNavigationId(screen)
    }

    private fun getNavigationId(screen: HealthAppTabFragmentScreen) =
        when (screen) {
            HomeTabScreen -> R.id.navigation_home
            AppointmentsTabScreen -> R.id.navigation_sppointments
            DoctorSearchTabScreen -> R.id.navigation_doctor_search
            ProfileTabScreen -> R.id.navigation_profile
            else -> R.id.navigation_home
        }

    data class ScreenView(
        val screen: HealthAppTabFragmentScreen,
        val view: DiTabContainerFragment<*, *, *, *>?,
        val tag: String
    )

    object Tags {
        const val HOME_TAB_TAG = "HomeTabView"
        const val APPOINTMENT_TAB_TAG = "AppointmentsTabView"
        const val DOCTOR_SEARCH_TAB_TAG = "DoctorSearchTabView"
        const val PROFILE_TAB_TAG = "ProfileTabView"

        fun getTags() = listOf<String>(
            HOME_TAB_TAG, APPOINTMENT_TAB_TAG, DOCTOR_SEARCH_TAB_TAG,
            PROFILE_TAB_TAG
        )
    }

}
